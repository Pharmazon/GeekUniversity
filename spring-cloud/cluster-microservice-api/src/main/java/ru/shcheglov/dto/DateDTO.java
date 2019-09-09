package ru.shcheglov.dto;

import java.util.Date;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

public class DateDTO {

    private Date value;

    public DateDTO(Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}