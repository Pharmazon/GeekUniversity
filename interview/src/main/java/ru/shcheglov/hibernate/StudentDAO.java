package ru.shcheglov.hibernate;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student> {

    @Override
    public List<Student> getAll() {
        return getSession()
                .createNamedQuery("Student.getAll", Student.class)
                .getResultList();
    }

    public Student getOneByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        Root<Student> from = criteria.from(Student.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("name"), name));
        TypedQuery<Student> typed = getSession().createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
