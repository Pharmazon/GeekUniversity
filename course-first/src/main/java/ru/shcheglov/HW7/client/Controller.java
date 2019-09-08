package ru.shcheglov.HW7.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
        if (authorized) {
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
            authPanel.setVisible(false);
            authPanel.setManaged(false);
        } else {
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
            authPanel.setVisible(true);
            authPanel.setManaged(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/authok")) {
                                setAuthorized(true);
                                String[] data = msg.split("\\s");
                                String nick = data[1];
                                break;
                            }
                            textArea.appendText(msg + "\n");
                        }
                        while (true) {
                            String msg = in.readUTF();
                            textArea.appendText(msg + "\n");
                        }
                    } catch (IOException e) {
                        showAlert("Couldn't read the input message", Alert.AlertType.ERROR);
                    } finally {
                        setAuthorized(false);
                        try {
                            socket.close();
                        } catch (IOException e) {
                            showAlert("Couldn't close client socket", Alert.AlertType.ERROR);
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            showAlert("Couldn't create the input/output stream", Alert.AlertType.ERROR);
        }
    }

    public void sendAuthMsg() {
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
        } catch (IOException e) {
            showAlert("Couldn't send the authentication message", Alert.AlertType.ERROR);
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            showAlert("Couldn't send the message", Alert.AlertType.ERROR);
        }
    }

    public static void showAlert(String msg, Alert.AlertType alertType) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
            }
        });
    }
}




