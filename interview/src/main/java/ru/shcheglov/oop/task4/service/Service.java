package ru.shcheglov.homework1.task4.service;

import ru.shcheglov.homework1.task4.entity.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    void add(T entity);

    List<T> getAll();

    void remove(T entity);

    void removeById(int id);

    T getById(int id);

    void removeAll();
}
