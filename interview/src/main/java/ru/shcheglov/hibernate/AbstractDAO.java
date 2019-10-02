package ru.shcheglov.hibernate;

import lombok.Getter;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Getter
public abstract class AbstractDAO<T> implements DAO<T> {

    private Session session;

    private Class<T> clazz;

    public void openSession() {
        this.session = Factory.getInstance().getSessionFactory().getCurrentSession();
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        beginTransaction();
    }

    public void addOne(T entity) {
        session.save(entity);
    }

    public void saveOrUpdate(T entity) {
        session.saveOrUpdate(entity);
    }

    public void save(T entity) {
        session.save(entity);
    }

    public void persist(T entity) {
        session.persist(entity);
    }


    public void delete(T entity) {
        session.delete(entity);
    }

    public void closeSession() {
        commitTransaction();
        if (session != null) {
            session.close();
        }
    }

    public void commitTransaction() {
        session.getTransaction().commit();
    }

    public void beginTransaction() {
        session.beginTransaction();
    }

    public void rollbackTransaction() {
        session.getTransaction().rollback();
    }

    public T getOneById(Long id) {
        return session.find(clazz, id);
    }

    public abstract List<T> getAll();
}
