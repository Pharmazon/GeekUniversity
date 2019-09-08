package ru.cloud.storage.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.cloud.storage.client.config.Messages;
import ru.cloud.storage.client.util.Network;
import ru.cloud.storage.client.util.WindowManager;
import ru.cloud.storage.common.message.AuthMsg;

public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private void initialize() {
        loginField.setText("arty2k@yandex.ru");
        passField.setText("0");
    }

    public void btnSignUp() {
        WindowManager.showRegister();
    }

    public void btnChangePassword() {
        WindowManager.showChangePassword();
    }

    public void sendAuthMsg() {
        if (loginField.getText().isEmpty() || passField.getText().isEmpty()) {
            WindowManager.showWarningAlert(Messages.WRNG_NOT_ALL_DATA);
            return;
        }
        AuthMsg msg = new AuthMsg(loginField.getText(), passField.getText());
        Network.getInstance().sendObject(msg);
    }
}
