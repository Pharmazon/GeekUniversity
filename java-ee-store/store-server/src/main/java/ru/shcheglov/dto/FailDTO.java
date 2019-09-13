package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alexey Shcheglov
 * @version dated 03.02.2019
 */

@XmlRootElement(name = "fail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FailDTO extends ResultDTO {

    {
        setSuccess(false);
    }

    public FailDTO(Exception e) {
        setSuccess(false);
    }

}
