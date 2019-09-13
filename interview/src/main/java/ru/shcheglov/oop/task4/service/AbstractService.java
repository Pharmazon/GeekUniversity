package ru.shcheglov.homework1.task4.service;

import ru.shcheglov.homework1.task4.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    private List<T> list = new ArrayList<>();

    public void add(T entity) {
        entity.setId(list.size() + 1);
        list.add(entity);
    }

    public List<T> getAll() {
        return list;
    }

    public void remove(T entity) {
        list.remove(entity);
    }

    public void removeById(int id) {
        T entity = getById(id);
        if (entity != null) remove(entity);
    }

    public T getById(int id) {
        Optional<T> optional = list.stream()
                .filter(elem -> elem.getId() == id)
                .findFirst();
        return optional.orElse(null);
    }

    public void removeAll() {
        list.clear();
    }
}
