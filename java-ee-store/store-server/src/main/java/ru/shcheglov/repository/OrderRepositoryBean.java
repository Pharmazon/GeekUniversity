package ru.shcheglov.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Order;
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
public class OrderRepositoryBean extends AbstractRepository<Order> implements OrderRepository {

    @Override
    public Collection<Order> findAll() {
        return getEntityManager()
                .createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    @Override
    public Order findOneById(@Nullable final String id) {
        if (id == null) return null;
        return getEntityManager().find(Order.class, id);
    }

    @Override
    public Collection<Order> findAllByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @Nullable final Collection<Order> result = new LinkedList<>();
        for (@Nullable final String id : ids) {
            if (id == null || id.isEmpty()) continue;
            @Nullable final Order entity = findOneById(id);
            if (entity == null) continue;
            result.add(entity);
        }
        return result;
    }

    @Override
    public Collection<Order> merge(@Nullable final Collection<Order> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Order> result = new LinkedList<>();
        for (@Nullable final Order entity : entities) {
            if (entity == null) continue;
            result.add(merge(entity));
        }
        return result;
    }

    @Override
    public void removeAll() {
        getEntityManager()
                .createQuery("DELETE FROM Order o", Order.class)
                .executeUpdate();
    }

    @Override
    public void remove(@Nullable final Collection<Order> entities) {
        if (entities == null || entities.isEmpty()) return;
        for (@Nullable final Order entity : entities) {
            if (entity == null) continue;
            removeById(entity.getId());
        }
    }

    @Override
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        final Order entity = findOneById(id);
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

}
