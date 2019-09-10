package ru.shcheglov.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.CategoryDTO;
import ru.shcheglov.entity.Category;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@Transactional
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class CategoryRepositoryBean extends AbstractRepository<Category> implements CategoryRepository {

    @Override
    public Collection<Category> findAll() {
        return getEntityManager()
                .createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

    @Override
    public Category findOneById(@Nullable final String id) {
        if (id == null) return null;
        return getEntityManager().find(Category.class, id);
    }

    @Override
    public Collection<Category> findAllByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @Nullable final Collection<Category> result = new LinkedList<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            @Nullable final Category entity = findOneById(id);
            if (entity == null) continue;
            result.add(entity);
        }
        return result;
    }

    @Override
    public Collection<Category> merge(@Nullable final Collection<Category> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Category> result = new LinkedList<>();
        for (@Nullable final Category entity : entities) {
            if (entity == null) continue;
            result.add(merge(entity));
        }
        return result;
    }

    @Override
    public void removeAll() {
        getEntityManager()
                .createNamedQuery("Category.removeAll", Category.class)
                .executeUpdate();
    }

    @Override
    public void remove(@Nullable final Collection<Category> entities) {
        if (entities == null || entities.isEmpty()) return;
        for (@Nullable final Category entity : entities) {
            if (entity == null) continue;
            removeById(entity.getId());
        }
    }

    @Override
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        final Category entity = findOneById(id);
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
    public Category merge(@Nullable CategoryDTO dto) {
        final Category category = findOneById(dto.getId());
        merge(category);
        return category;
    }
}
