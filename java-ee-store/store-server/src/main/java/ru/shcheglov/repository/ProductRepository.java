package ru.shcheglov.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.ProductDTO;
import ru.shcheglov.entity.Product;

import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */


public interface ProductRepository extends CommonRepository<Product> {

    Collection<Product> findAllByCategoryId(@NotNull String categoryId);

    Collection<Product> findAllByName(@NotNull String name);

    Product merge(@Nullable ProductDTO dto);

}
