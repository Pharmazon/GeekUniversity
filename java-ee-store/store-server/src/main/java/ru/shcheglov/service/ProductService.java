package ru.shcheglov.service;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Product;

import java.util.Collection;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

public interface ProductService extends CommonService<Product> {

    List<Product> initProducts(@NotNull Integer quantity);

    Collection<Product> findAllByCategoryId(@NonNull String categoryId);

    Collection<Product> findAllByName(@NotNull String name);

    void addOne(@Nullable Product product);

    void addOneByName(@NotNull String name);
}
