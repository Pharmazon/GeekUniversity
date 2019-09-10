package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Product;

import java.util.Date;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private String id;

    private String name;

    private String categoryId;

    private String parentCategoryId;

    private Double price;

    private Integer quantity;

    private Double amount;

    private Date dateAdded;

    public ProductDTO(@Nullable final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.categoryId = product.getCategory().getId();
        this.parentCategoryId = product.getCategory().getParent().getId();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.amount = product.getAmount();
        this.dateAdded = product.getDateAdded();
    }
}
