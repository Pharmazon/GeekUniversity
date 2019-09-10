package ru.shcheglov.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.SessionDTO;
import ru.shcheglov.dto.UserDTO;
import ru.shcheglov.entity.Session;
import ru.shcheglov.entity.User;
import ru.shcheglov.exception.AccessForbiddenException;
import ru.shcheglov.interceptor.LogInterceptor;
import ru.shcheglov.repository.SessionRepository;
import ru.shcheglov.util.PasswordUtil;
import ru.shcheglov.util.SignatureUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@ManagedBean
@ApplicationScoped
@Interceptors({LogInterceptor.class})
public class SessionService implements Serializable {

    @Inject
    private SessionRepository sessionRepository;

    @Inject
    private UserService userService;

    @Inject
    private PropertyService propertyService;

    public Collection<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session findOneById(@Nullable final String id) {
        return sessionRepository.findOneById(id);
    }

    public void removeOneById(@Nullable final String id) {
        sessionRepository.removeById(id);
    }

    public Session findOneByUserId(final String userId) {
        return sessionRepository.findOneByUserId(userId);
    }

    public void closeAll(@Nullable final SessionDTO dto) {
        validate(dto);
        if (dto == null) return;
        final String userId = dto.getUserId();
        if (userId == null) return;;
        sessionRepository.removeByUserId(userId);
    }

    public Session update(@Nullable SessionDTO dto) {
        return sessionRepository.merge(dto);
    }

    public Session update(@Nullable Session session) {
        return sessionRepository.merge(session);
    }

    public Session create(@Nullable String userId) {
        if (userId == null) return null;
        final User user = userService.findOneById(userId);
        if (user == null) return null;
        final Long timestamp = System.currentTimeMillis();
        final Integer cycle = propertyService.getSessionCycle();
        final String salt = propertyService.getSessionSalt();
        final String signature = SignatureUtil.sign(this, salt, cycle);
        final Session session = new Session(timestamp, signature, user);
        update(session);
        return session;
    }

    public boolean isValid(@Nullable final SessionDTO dto) {
        try {
            validate(dto);
            return true;
        } catch (@NotNull final Exception e) {
            return false;
        }
    }

    @Nullable
    @SneakyThrows
    public SessionDTO sign(@Nullable final SessionDTO session) {
        if (session == null) return null;
        session.setSignature(null);
        final Integer cycle = propertyService.getSessionCycle();
        final String salt = propertyService.getSessionSalt();
        final String signature = SignatureUtil.sign(session, salt, cycle);
        session.setSignature(signature);
        return session;
    }

    @Nullable
    public String getUserId(@Nullable final SessionDTO session) {
        validate(session);
        return session.getUserId();
    }

    @Nullable
    public UserDTO getUser(@Nullable final SessionDTO session) {
        final String userId = getUserId(session);
        final User user = userService.findOneById(userId);
        return UserDTO.toDTO(user);
    }

    @SneakyThrows
    public void validate(@Nullable SessionDTO session) {
        if (session == null) throw new AccessForbiddenException();
        if (session.getSignature() == null || session.getSignature().isEmpty()) throw new AccessForbiddenException();
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new AccessForbiddenException();
        if (session.getTimestamp() == null) throw new AccessForbiddenException();
        final SessionDTO temp = session.clone();
        if (temp == null) throw new AccessForbiddenException();
        final String signatureSource = session.getSignature();
        final SessionDTO signatureTemp = sign(temp);
        if (signatureTemp == null) throw new AccessForbiddenException();
        final String signatureTarget = signatureTemp.getSignature();
        if (signatureSource == null) throw new AccessForbiddenException();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessForbiddenException();
        if (!contains(session.getId())) throw new AccessForbiddenException();
    }

    public SessionDTO open(@Nullable final String login, @Nullable final String password) {
        boolean check = userService.isExistByLogin(login);
        if (!check) return null;
        final User user = userService.findOneByLogin(login);
        if (user == null) return null;
        final String enteredPassword = PasswordUtil.encrypt(password);
        if (!user.getPasswordHash().equals(enteredPassword)) return null;
        SessionDTO dto = new SessionDTO();
        dto.setUserId(user.getId());
        dto.setTimestamp(System.currentTimeMillis());
        dto = sign(dto);
        final Session created = update(dto);
        return SessionDTO.toDTO(created);
    }

    @NotNull
    public Boolean contains(@Nullable final String id) {
        if (id == null || id.isEmpty()) return false;
        final Session session = sessionRepository.findOneById(id);
        if (session == null) return false;
        return true;
    }
}
