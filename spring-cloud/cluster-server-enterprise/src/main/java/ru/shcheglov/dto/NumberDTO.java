package ru.shcheglov.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alexey Shcheglov
 * @version dated 28.01.2019
 */

@XmlRootElement
public class NumberDTO {

    private Number result;

    public NumberDTO(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }
}
