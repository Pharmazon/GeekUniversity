package ru.shcheglov.service;

import org.jetbrains.annotations.NotNull;
import ru.shcheglov.constant.FieldConst;
import ru.shcheglov.entity.User;
import ru.shcheglov.util.PasswordUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Named
@RequestScoped
public class AuthService {

    @Inject
    private HttpServletRequest request;

    @Inject
    private UserService userService;

    public boolean openSession(final String login, final String password) {
        boolean exist = userService.isExistByLogin(login);
        if (!exist) return false;
        final User user = userService.findOneByLogin(login);
        if (user == null) return false;

        final String passwordHash = PasswordUtil.encrypt(password);
        boolean equals = passwordHash.equals(user.getPasswordHash());
        if (!equals) return false;

        final HttpSession session = request.getSession();
        session.setAttribute(FieldConst.USER_ID, user.getId());
        session.setAttribute(FieldConst.USER_LOGIN, user.getLogin());
        return true;
    }

    public void closeSession() {
        request.getSession().invalidate();
    }

    @NotNull
    public String getLogin() throws AuthException {
        final HttpSession session = request.getSession();
        if (session == null) throw new AuthException();
        final Object value = session.getAttribute(FieldConst.USER_LOGIN);
        if (value == null) throw new AuthException();
        return (String) value;
    }

    @NotNull
    public String getUserId() throws AuthException {
        final HttpSession session = request.getSession();
        if (session == null) throw new AuthException();
        final Object value = session.getAttribute(FieldConst.USER_ID);
        if (value == null) throw new AuthException();
        return (String) value;
    }

    public boolean isAuth() {
        final HttpSession session = request.getSession();
        return isAuth(session);
    }

    public static boolean isAuth(final HttpSession session) {
        if (session == null) return false;
        final boolean hasLogin = session.getAttribute(FieldConst.USER_LOGIN) != null;
        final boolean hasUserId = session.getAttribute(FieldConst.USER_ID) != null;
        return hasLogin && hasUserId;
    }
}
