package ru.shcheglov.service;

import org.jetbrains.annotations.NotNull;
import ru.shcheglov.entity.User;
import ru.shcheglov.interceptor.LogInterceptor;
import ru.shcheglov.repository.UserRepository;
import ru.shcheglov.util.PasswordUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@ManagedBean
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class UserService {

    @Inject
    private UserRepository userRepository;

    public User findOneByLogin(@NotNull final String login) {
        return userRepository.findOneByLogin(login);
    }

    public boolean isExistByLogin(@NotNull final String login) {
        final User user = findOneByLogin(login);
        return user != null;
    }

    public boolean isExistByUserId(@NotNull final String userId) {
        final User user = userRepository.findOneById(userId);
        return user != null;
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    public User findOneById(@NotNull final String userId) {
        return userRepository.findOneById(userId);
    }

    public void removeOneById(@NotNull final String userId) {
        userRepository.removeById(userId);
    }

    public void register(@NotNull final String login, @NotNull final String password) {
        if (login == null || login.isEmpty()) return;
        if (password == null || password.isEmpty()) return;
        if (isExistByLogin(login)) return;
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(PasswordUtil.encrypt(password));
        userRepository.merge(user);
    }

}
