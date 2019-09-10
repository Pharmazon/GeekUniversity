package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import ru.shcheglov.entity.Category;
import ru.shcheglov.service.CategoryService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ViewScoped
@ManagedBean
@URLMapping(
        id = "admin-category-list",
        pattern = "/admin/admin-category-list",
        viewId = "/WEB-INF/faces/admin-category-list.xhtml"
)
public class AdminCategoryListController implements Serializable {

    @Inject
    private CategoryService categoryService;

    public Collection<Category> getListCategories() {
        return categoryService.findAll();
    }

    public void removeCategoryById(final String id) {
        categoryService.removeById(id);
    }

}
