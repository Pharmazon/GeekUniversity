package ru.shcheglov.HW8.client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8189;

    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    boolean authorized;

    private ObservableList<String> clientsList;
    private String nickname;

    @FXML
    TextArea textArea;
    @FXML
    TextField msgField;
    @FXML
    HBox msgPanel;
    @FXML
    HBox authPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passField;
    @FXML
    ListView<String> clientsListView;

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
        if (authorized) {
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            clientsListView.setVisible(true);
            clientsListView.setManaged(true);
        } else {
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
            authPanel.setVisible(true);
            authPanel.setManaged(true);
            clientsListView.setVisible(false);
            clientsListView.setManaged(false);
            nickname = "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthorized(false);
    }

    public void connect() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            clientsList = FXCollections.observableArrayList();
            clientsListView.setItems(clientsList);

            clientsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new ListCell<String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!empty) {
                                setText(item);
                                if (item.equals(nickname)){
                                    setStyle("-fx-font-weight: bold; -fx-background-color: #00FFFF");
                                }
                            } else {
                                setGraphic(null);
                                setText(null);
                            }
                        }
                    };
                }
            });

            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/authok ")) {
                            setAuthorized(true);
                            nickname = msg.split("\\s")[1];
                            break;
                        }
                        textArea.appendText(msg + "\n");
                    }

                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/")) {
                            if (msg.startsWith("/clientslist ")) {
                                String[] data = msg.split("\\s");
                                {
                                    Platform.runLater(() -> {
                                        clientsList.clear();
                                        for (int i = 1; i < data.length; i++) {
                                            clientsList.addAll(data[i]);
                                        }
                                    });
                                }
                            }
                        } else {
                            textArea.appendText(msg + "\n");
                        }
                    }
                } catch (IOException e) {
                    showAlert("120 sec. passed. Please, restart chat.", Alert.AlertType.INFORMATION);
                } finally {
                    setAuthorized(false);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        showAlert("Couldn't close client socket", Alert.AlertType.ERROR);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            showAlert("Couldn't connect to server. Check your internet connection.", Alert.AlertType.ERROR);
        }
    }

    public void sendAuthMsg() {
        if (loginField.getText().isEmpty() || passField.getText().isEmpty()) {
            showAlert("Not all authorization data is specified", Alert.AlertType.WARNING);
            return;
        }
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
        } catch (IOException e) {
            showAlert("Couldn't connect to server. Check your internet connection.", Alert.AlertType.ERROR);
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            showAlert("Couldn't connect to server. Check your internet connection.", Alert.AlertType.ERROR);
        }
    }

    public static void showAlert(String msg, Alert.AlertType alertType) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            if (alertType == Alert.AlertType.INFORMATION) {
                alert.setTitle("Information");
            } else if (alertType == Alert.AlertType.ERROR) {
                alert.setTitle("Error");
            } else if (alertType == Alert.AlertType.WARNING) {
                alert.setTitle("Warning");
            }
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }

    //call private chat on mouse double-click
    public void clientsListClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            msgField.setText("/private " + clientsListView.getSelectionModel().getSelectedItem() + " ");
            msgField.requestFocus();
            msgField.selectEnd();
        }
    }
}




