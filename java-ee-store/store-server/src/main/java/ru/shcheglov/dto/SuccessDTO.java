package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alexey Shcheglov
 * @version dated 03.02.2019
 */

@XmlRootElement(name = "success")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessDTO extends ResultDTO {

    {
        setSuccess(true);
    }

}
