package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * @author Alexey Shcheglov
 * @version dated 03.02.2019
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO {

    @Nullable
    private Boolean success = true;

}
