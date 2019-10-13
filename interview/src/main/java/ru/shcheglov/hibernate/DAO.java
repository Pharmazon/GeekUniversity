package ru.shcheglov.hibernate;

import java.util.List;

public interface DAO<T> {

    void openSession();

    List<T> getAll();

    T getOneById(Long id);

    void addOne(T entity);

    void saveOrUpdate(T entity);

    void persist(T entity);

    void save(T entity);

    void delete(T entity);

    void closeSession();

    void commitTransaction();

    void beginTransaction();

    void rollbackTransaction();

}
