package ru.shcheglov.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 01.02.2019
 */

@Getter
@Setter
@ApplicationScoped
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    private List<Product> products;

    public void addProduct(@NotNull final Product product) {
        products.add(product);
    }

    public void clearCart() {
        products.clear();
    }

    public void removeProductByIndex(final int index) {
        products.remove(index);
    }

    public void removeProduct(@NonNull final Product product) {
        products.remove(product);
    }

    public Integer size() {
        return products.size();
    }

    public Product getProductByIndex(final int index) {
        return products.get(index);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean isContainsProduct(@NotNull final Product product) {
        return products.contains(product);
    }

    public int getCartTotalQuantity() {
        int result = 0;
        for (final Product product : products) {
            if (product == null) continue;
            result = result + product.getQuantity();
        }
        return result;
    }

    public double getCartTotalAmount() {
        double result = 0.00;
        for (final Product product : products) {
            if (product == null) continue;
            result = result + product.getAmount();
        }
        return result;
    }

}
