package ru.shcheglov.repository;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.SessionDTO;
import ru.shcheglov.entity.Session;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Transactional
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class SessionRepository extends AbstractRepository<Session>  {

    public Collection<Session> findAll() {
        return getEntityManager()
                .createQuery("SELECT s FROM Session s", Session.class)
                .getResultList();
    }

    public Session findOneById(@Nullable final String id) {
        if (id == null) return null;
        return getEntityManager().find(Session.class, id);
    }

    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        final Session session = findOneById(id);
        if (session == null) return;
        removeOne(session);
    }

    public void removeByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return;
        getEntityManager()
                .createQuery("DELETE FROM Session s WHERE s.user.id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public Session findOneByUserId(final String userId) {
        if (userId == null) return null;
        final TypedQuery<Session> query = getEntityManager()
                .createQuery("SELECT s FROM Session s INNER JOIN FETCH s.user u WHERE u.id = :userId", Session.class)
                .setParameter("userId", userId);
        return getEntity(query);
    }

    public Session merge(@Nullable SessionDTO dto) {
        Session session = findOneById(dto.getId());
        merge(session);
        return session;
    }

}
