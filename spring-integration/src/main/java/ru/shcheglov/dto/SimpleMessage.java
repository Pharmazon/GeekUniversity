package ru.shcheglov.dto;

import java.util.Date;

public class SimpleMessage {

    private Date date;

    public SimpleMessage() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
