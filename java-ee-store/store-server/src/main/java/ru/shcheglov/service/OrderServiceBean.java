package ru.shcheglov.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Order;
import ru.shcheglov.interceptor.LogInterceptor;
import ru.shcheglov.repository.OrderRepository;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

@ManagedBean
@NoArgsConstructor
@Interceptors({LogInterceptor.class})
public class OrderServiceBean implements OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOneById(@Nullable String id) {
        return orderRepository.findOneById(id);
    }

    @Override
    public Collection<Order> findAllByIds(@Nullable Collection<String> ids) {
        return orderRepository.findAllByIds(ids);
    }

    @Override
    public Order merge(@Nullable Order entity) {
        return orderRepository.merge(entity);
    }

    @Override
    public Collection<Order> merge(@Nullable Collection<Order> entities) {
        return orderRepository.merge(entities);
    }

    @Override
    public void removeAll() {
        orderRepository.removeAll();
    }

    @Override
    public void remove(@Nullable Collection<Order> entities) {
        orderRepository.remove(entities);
    }

    @Override
    public void removeById(@Nullable String id) {
        orderRepository.removeById(id);
    }

    @Override
    public void removeByIds(@Nullable Collection<String> ids) {
        orderRepository.removeByIds(ids);
    }

}
