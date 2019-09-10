package ru.shcheglov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_categories")
@NamedQueries({
        @NamedQuery(name = "Category.removeAll" , query = "DELETE FROM Category c")
})
public class Category extends AbstractEntity {

    @Nullable
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Nullable
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(@Nullable String name) {
        super(name);
    }

}
