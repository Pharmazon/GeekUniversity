package ru.shcheglov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_products")
public class Product extends AbstractEntity {

    @Nullable
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Nullable
    @Column(name = "price")
    private Double price;

    @Nullable
    @Column(name = "quantity")
    private Integer quantity;

    @Nullable
    @Column(name = "amount")
    private Double amount;

    @Nullable
    @Column(name = "date_added")
    private Date dateAdded;

    public Product(@Nullable String name) {
        super(name);
    }

}
