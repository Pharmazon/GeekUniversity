package ru.shcheglov.service;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.shcheglov.entity.Cart;
import ru.shcheglov.entity.Product;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.Serializable;

/**
 * @author Alexey Shcheglov
 * @version dated 02.02.2019
 */

@ManagedBean
@SessionScoped
@Interceptors({LogInterceptor.class})
public class CartBean implements Serializable {

    @Inject
    private Cart cart;

    public void addProduct(@NotNull final Product product) {
       cart.addProduct(product);
    }

    public void clearCart() {
        cart.clearCart();
    }

    public void removeProductByIndex(final int index) {
        cart.removeProductByIndex(index);
    }

    public void removeProduct(@NonNull final Product product) {
        cart.removeProduct(product);
    }

    public Integer size() {
        return cart.size();
    }

    public Product getProductByIndex(final int index) {
        return cart.getProductByIndex(index);
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public boolean isContainsProduct(@NotNull final Product product) {
        return cart.isContainsProduct(product);
    }

    public int getCartTotalQuantity() {
        return cart.getCartTotalQuantity();
    }

    public double getCartTotalAmount() {
        return cart.getCartTotalAmount();
    }

}
