package ru.shcheglov.HW6.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8189;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread thread = new Thread(() -> {
                try {
                    String msg;
                    while (true) {
                        msg = in.readUTF();
                        textArea.appendText(msg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
