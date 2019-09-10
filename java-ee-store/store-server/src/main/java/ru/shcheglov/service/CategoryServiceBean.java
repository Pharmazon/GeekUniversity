package ru.shcheglov.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Category;
import ru.shcheglov.interceptor.LogInterceptor;
import ru.shcheglov.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ManagedBean
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class CategoryServiceBean implements CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOneById(@Nullable String id) {
        return categoryRepository.findOneById(id);
    }

    @Override
    public Collection<Category> findAllByIds(@Nullable Collection<String> ids) {
        return categoryRepository.findAllByIds(ids);
    }

    @Override
    public Category merge(@Nullable Category entity) {
        return categoryRepository.merge(entity);
    }

    @Override
    public Category addOne(@NotNull final String name) {
        final Category category = new Category();
        category.setName(name);
        return categoryRepository.merge(category);
    }

    @Override
    public Collection<Category> merge(@Nullable Collection<Category> entities) {
        return categoryRepository.merge(entities);
    }

    @Override
    public void removeAll() {
        categoryRepository.removeAll();
    }

    @Override
    public void remove(@Nullable Collection<Category> entities) {
        categoryRepository.remove(entities);
    }

    @Override
    public void removeById(@Nullable String id) {
        categoryRepository.removeById(id);
    }

    @Override
    public void removeByIds(@Nullable Collection<String> ids) {
        categoryRepository.removeByIds(ids);
    }

    @Override
    public String getParent(@Nullable String id) {
        final Category category = findOneById(id);
        return category.getParent().toString();
    }

}
