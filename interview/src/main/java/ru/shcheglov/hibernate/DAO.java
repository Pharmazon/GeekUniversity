package ru.shcheglov.hibernate;

import java.util.List;

public interface DAO<T> {

    void openSession();

    List<T> getAll();

    T getOneById(Long id);

    void addOne(T entity);

    void update(T entity);

    void delete(T entity);

    void close();

}
