package ru.cloud.storage.client.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import ru.cloud.storage.client.util.Assets;
import ru.cloud.storage.common.util.MathFunc;

public class ProgressController {
    @FXML
    private ProgressBar progressBar = new ProgressBar();

    @FXML
    private Label label = new Label();

    @FXML
    private Button cancelButton = new Button();

    @FXML
    private void initialize() {
        Assets.getInstance().setProgressController(this);
        progressBar.setProgress(0);
        cancelButton.setDisable(false);
//        progressBar.progressProperty().unbind();
//        label.textProperty().unbind();
        label.setText("0.00%");
    }

    public void setProgress(double value) {
        Platform.runLater(() -> {
            double val = MathFunc.round(value, 2);
            progressBar.setProgress(val);
            label.setText(String.valueOf(val) + "%");
        });
    }

    public void cancel() {
        cancelButton.setDisable(true);
        label.setText("0.00%");
//        progressBar.progressProperty().unbind();
//        label.textProperty().unbind();
        progressBar.setProgress(0);
    }
}
