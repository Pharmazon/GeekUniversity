package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import ru.shcheglov.entity.Order;
import ru.shcheglov.service.OrderService;

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
        id = "admin-order-list",
        pattern = "/admin/admin-order-list",
        viewId = "/WEB-INF/faces/admin-order-list.xhtml"
)
public class AdminOrderListController implements Serializable {

    @Inject
    private OrderService orderService;

    public Collection<Order> getListOrders() {
        return orderService.findAll();
    }

    public void removeOrderById(final String id) {
        orderService.removeById(id);
    }

}
