package ru.shcheglov.service;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Product;
import ru.shcheglov.interceptor.LogInterceptor;
import ru.shcheglov.repository.CategoryRepository;
import ru.shcheglov.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ManagedBean
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class ProductServiceBean implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOneById(@Nullable String id) {
        return productRepository.findOneById(id);
    }

    @Override
    public Collection<Product> findAllByIds(@Nullable Collection<String> ids) {
        return productRepository.findAllByIds(ids);
    }

    @Override
    public Product merge(@Nullable Product entity) {
        return productRepository.merge(entity);
    }

    @Override
    public Collection<Product> merge(@Nullable Collection<Product> entities) {
        return productRepository.merge(entities);
    }

    @Override
    public void removeAll() {
        productRepository.removeAll();
    }

    @Override
    public void remove(@Nullable Collection<Product> entities) {
        productRepository.remove(entities);
    }

    @Override
    public void removeById(@Nullable String id) {
        productRepository.removeById(id);
    }

    @Override
    public void removeByIds(@Nullable Collection<String> ids) {
        productRepository.removeByIds(ids);
    }

    public List<Product> initProducts(@NotNull Integer quantity) {
        final List<Product> resultList = new LinkedList<>();
        for (int i = 1; i <= quantity; i++) {
            final Product product = new Product("Product-" + i);
            resultList.add(product);
            productRepository.merge(product);
        }
        return resultList;
    }

    @Override
    public Collection<Product> findAllByCategoryId(@NonNull final String categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Collection<Product> findAllByName(@NotNull final String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public void addOne(@Nullable final Product product) {
        merge(product);
    }

    @Override
    public void addOneByName(@NotNull final String name) {
        final Product product = new Product(name);
        merge(product);
    }
}
