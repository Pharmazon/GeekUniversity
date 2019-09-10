package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Product;
import ru.shcheglov.service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ViewScoped
@ManagedBean
@URLMapping(
        id = "admin-product-edit",
        pattern = "/admin/admin-product-edit",
        viewId = "/WEB-INF/faces/admin-product-edit.xhtml"
)
public class AdminProductEditController implements Serializable {

    @Inject
    private ProductService productService;

    @Getter
    @Setter
    @Nullable
    private String id;

    @Getter
    @Setter
    @NotNull
    private Product product = new Product();

    public void init() {
        @Nullable final Product product = productService.findOneById(id);
        if (product != null) this.product = product;
    }

    @NotNull
    public String save() {
        productService.merge(product);
        return "product-list";
    }

}
