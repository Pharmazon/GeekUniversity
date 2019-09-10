package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Category;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private String id;

    private String name;

    private String parentId;

    public CategoryDTO(@Nullable final Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentId = category.getParent().getId();
    }

}
