package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Category;
import ru.shcheglov.service.CategoryService;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ViewScoped
@ManagedBean
@URLMapping(
        id = "admin-category-edit",
        pattern = "/admin/admin-category-edit",
        viewId = "/WEB-INF/faces/admin-category-edit.xhtml"
)
public class AdminCategoryEditController implements Serializable {

    @Inject
    private CategoryService categoryService;

    @Getter
    @Setter
    @Nullable
    private String id;

    @Getter
    @Setter
    @NotNull
    private Category category = new Category();

    public void init() {
        @Nullable final Category category = categoryService.findOneById(id);
        if (category != null) this.category = category;
    }

    @NotNull
    public String save() {
        categoryService.merge(category);
        return "category-list";
    }

}
