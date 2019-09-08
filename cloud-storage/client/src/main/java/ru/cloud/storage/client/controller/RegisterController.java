package ru.cloud.storage.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.cloud.storage.client.config.Messages;
import ru.cloud.storage.client.util.Network;
import ru.cloud.storage.client.util.WindowManager;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;

public class RegisterController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passFieldPrimary;

    @FXML
    private PasswordField passFieldSecondary;

    public void btnBack() {
        WindowManager.showLogin();
    }

    public void btnRegister() {
        if (loginField.getText().isEmpty() || nameField.getText().isEmpty() || passFieldPrimary.getText().isEmpty() ||
                passFieldSecondary.getText().isEmpty()) {
            WindowManager.showWarningAlert(Messages.WRNG_NOT_ALL_DATA);
            return;
        }
        if (!passFieldPrimary.getText().equals(passFieldSecondary.getText())) {
            WindowManager.showWarningAlert(Messages.WRNG_DONT_MATCH);
            return;
        }
        CmdMsg cmdMsg = new CmdMsg(Commands.REGISTER, loginField.getText(), passFieldPrimary.getText(),
                nameField.getText());
        Network.getInstance().sendObject(cmdMsg);
    }
}
