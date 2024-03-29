package ru.shcheglov.repository;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.AbstractEntity;

import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

public interface CommonRepository<T extends AbstractEntity> {

    Collection<T> findAll();

    T findOneById(@Nullable String id);

    Collection<T> findAllByIds(@Nullable Collection<String> ids);

    T merge(@Nullable T entity);

    Collection<T> merge(@Nullable Collection<T> entities);

    void removeAll();

    void remove(@Nullable Collection<T> entities);

    void removeById(@Nullable String id);

    void removeByIds(@Nullable Collection<String> ids);

}
