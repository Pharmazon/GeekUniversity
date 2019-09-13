package ru.shcheglov.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.ProductDTO;
import ru.shcheglov.entity.Product;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

@Transactional
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class ProductRepositoryBean extends AbstractRepository<Product> implements ProductRepository {

    @Override
    public Collection<Product> findAll() {
        return getEntityManager()
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product findOneById(@Nullable final String id) {
        if (id == null) return null;
        return getEntityManager().find(Product.class, id);
    }

    @Override
    public Collection<Product> findAllByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @Nullable final Collection<Product> result = new LinkedList<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            @Nullable final Product entity = findOneById(id);
            if (entity == null) continue;
            result.add(entity);
        }
        return result;
    }

    @Override
    public Collection<Product> merge(@Nullable final Collection<Product> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Product> result = new LinkedList<>();
        for (@Nullable final Product entity : entities) {
            if (entity == null) continue;
            result.add(merge(entity));
        }
        return result;
    }

    @Override
    public void removeAll() {
        getEntityManager()
                .createQuery("DELETE FROM Product o", Product.class)
                .executeUpdate();
    }

    @Override
    public void remove(@Nullable final Collection<Product> entities) {
        if (entities == null || entities.isEmpty()) return;
        for (@Nullable final Product entity : entities) {
            if (entity == null) continue;
            removeById(entity.getId());
        }
    }

    @Override
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        final Product entity = findOneById(id);
        if (entity == null) return;
        removeOne(entity);
    }

    @Override
    public void removeByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            removeById(id);
        }
    }

    @Override
    public Collection<Product> findAllByCategoryId(@NotNull final String categoryId) {
        return getEntityManager()
                .createQuery("SELECT p FROM Product p INNER JOIN FETCH p.category c WHERE c.id = :categoryId",
                        Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public Collection<Product> findAllByName(@NotNull final String name) {
        return getEntityManager()
                .createQuery("SELECT p FROM Product p WHERE p.name = :productName", Product.class)
                .setParameter("productName", name)
                .getResultList();
    }

    @Override
    public Product merge(@Nullable ProductDTO dto) {
        final Product product = findOneById(dto.getId());
        merge(product);
        return product;
    }

}
