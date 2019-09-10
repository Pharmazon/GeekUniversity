package ru.shcheglov.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * @author Alexey Shcheglov
 * @version dated 19.01.2019
 */

public abstract class AbstractRepository<T extends AbstractEntity> {

    @Getter
    @PersistenceContext
    private EntityManager entityManager;

    public void saveOne(@Nullable T entity) {
        entityManager.persist(entity);
    }

    public void saveAll(@Nullable final Collection<T> entities) {
        if (entities == null || entities.isEmpty()) return;
        for (T entity : entities) saveOne(entity);
    }

    public T merge(@Nullable T entity) {
        return entityManager.merge(entity);
    }

    public void removeOne(@Nullable T entity) {
        entityManager.remove(entity);
    }

    public T getEntity(@NotNull final TypedQuery<T> query) {
        final List<T> list = query.getResultList();
        if (list.isEmpty()) return null;
        return list.get(0);
    }

}
