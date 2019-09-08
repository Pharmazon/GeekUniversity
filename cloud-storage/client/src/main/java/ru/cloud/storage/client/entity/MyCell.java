package ru.cloud.storage.client.entity;

import javafx.scene.control.TableCell;

public class MyCell extends TableCell<TableEntry, String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        if (!empty) {
//            if (item.size() > 0) {
//                setStyle("-fx-background-insets: 1, 2; -fx-background-radius: 3, 3; -fx-background-color: #000000, " +
//                        "linear-gradient(#99ff99 0%, #00bb00 40%, #008800 60%, #000000 100%)");
//            } else {
            setStyle("-fx-font-weight: bold;");
//            }
        }
        super.updateItem(item, empty);
    }
}
