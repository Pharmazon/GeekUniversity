package ru.shcheglov.hibernate;

import lombok.Getter;
import org.hibernate.Session;

@Getter
public abstract class AbstractDAO<T> {

    private Session session;

    public void openSession() {
        this.session = Factory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void addOne(T entity) {
        session.save(entity);
    }

    public void update(T entity) {
        session.saveOrUpdate(entity);
    }

    public void delete(T entity) {
        session.delete(entity);
    }

    public void close() {
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
}
