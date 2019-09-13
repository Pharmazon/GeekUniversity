package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Order;
import ru.shcheglov.service.OrderService;

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
        id = "admin-order-edit",
        pattern = "/admin/admin-order-edit",
        viewId = "/WEB-INF/faces/admin-order-edit.xhtml"
)
public class AdminOrderEditController implements Serializable {

    @Inject
    private OrderService orderService;

    @Getter
    @Setter
    @Nullable
    private String id;

    @Getter
    @Setter
    @NotNull
    private Order order = new Order();

    public void init() {
        @Nullable final Order order = orderService.findOneById(id);
        if (order != null) this.order = order;
    }

    @NotNull
    public String save() {
        orderService.merge(order);
        return "product-list";
    }

}
