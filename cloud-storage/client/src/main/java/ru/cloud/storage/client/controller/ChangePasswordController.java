package ru.cloud.storage.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.cloud.storage.client.config.Messages;
import ru.cloud.storage.client.util.Network;
import ru.cloud.storage.client.util.WindowManager;
import ru.cloud.storage.common.Commands;
import ru.cloud.storage.common.message.CmdMsg;

public class ChangePasswordController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passOldField;

    @FXML
    private PasswordField passNewFieldPrimary;

    @FXML
    private PasswordField passNewFieldSecondary;

    public void btnBack() {
        WindowManager.showLogin();
    }

    public void btnChangePassword() {
        if (loginField.getText().isEmpty() || passOldField.getText().isEmpty() ||
                passNewFieldPrimary.getText().isEmpty() || passNewFieldSecondary.getText().isEmpty()) {
            WindowManager.showWarningAlert(Messages.WRNG_NOT_ALL_DATA);
            return;
        }
        if (!passNewFieldPrimary.getText().equals(passNewFieldSecondary.getText())) {
            WindowManager.showWarningAlert(Messages.WRNG_DONT_MATCH);
            return;
        }
        if (passOldField.getText().equals(passNewFieldPrimary.getText())) {
            WindowManager.showWarningAlert(Messages.WRNG_OLD_NEW_PASS);
            return;
        }
        Network.getInstance().checkConnected();
        CmdMsg cmdMsg = new CmdMsg(Commands.CHANGEPASS, loginField.getText(), passOldField.getText(),
                passNewFieldPrimary.getText());
        Network.getInstance().sendObject(cmdMsg);
    }
}
