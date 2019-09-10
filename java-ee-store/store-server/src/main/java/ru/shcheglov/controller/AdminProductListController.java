package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Product;
import ru.shcheglov.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ViewScoped
@ManagedBean
@URLMapping(
        id = "admin-product-list",
        pattern = "/admin/admin-product-list",
        viewId = "/WEB-INF/faces/admin-product-list.xhtml"
)
public class AdminProductListController implements Serializable {

    @Inject
    private ProductService productService;

    @Getter
    @Setter
    @NotNull
    private List<Product> products = new LinkedList<>();

    @PostConstruct
    private void init() {
        reload();
    }

    private void reload() {
        products.clear();;
        products.addAll(productService.findAll());
    }

    public void removeProductById(@Nullable final String id) {
        productService.removeById(id);
        reload();
    }

}
