package ru.shcheglov.repository;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.UserDTO;
import ru.shcheglov.entity.User;
import ru.shcheglov.interceptor.LogInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@Transactional
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class UserRepository extends AbstractRepository<User> {
    
    public Collection<User> findAll() {
        return getEntityManager()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
    
    public User findOneById(@Nullable final String id) {
        if (id == null) return null;
        return getEntityManager().find(User.class, id);
    }
    
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        final User entity = findOneById(id);
        if (entity == null) return;
        removeOne(entity);
    }
    
    public User findOneByLogin(final String login) {
        if (login == null) return null;
        final TypedQuery<User> query = getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.login = :userLogin", User.class)
                .setParameter("userLogin", login);
        return getEntity(query);
    }

    public User merge(@Nullable UserDTO dto) {
        final User user = findOneById(dto.getId());
        merge(user);
        return user;
    }

}
