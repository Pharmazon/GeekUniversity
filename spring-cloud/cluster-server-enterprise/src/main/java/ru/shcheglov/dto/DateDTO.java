package ru.shcheglov.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@XmlRootElement
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