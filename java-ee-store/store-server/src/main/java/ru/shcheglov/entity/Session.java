package ru.shcheglov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shcheglov.dto.SessionDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_sessions")
public final class Session extends AbstractEntity {

    public Session clone() {
        try {
            return (Session) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "signature")
    private String signature;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static List<SessionDTO> toDTO(final Collection<Session> sessions) {
        if (sessions == null || sessions.isEmpty()) return null;
        return sessions.stream()
                .map(SessionDTO::new)
                .collect(Collectors.toList());
    }

    public static SessionDTO toDTO(final Session session) {
        if (session == null) return null;
        return new SessionDTO(session);
    }
}
