package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.shcheglov.entity.Session;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO {

    private String id;

    private Long timestamp;

    private String userId;

    private String signature;

    public SessionDTO(@NotNull final Session session) {
        this.id = session.getId();
        this.timestamp = session.getTimestamp();
        this.userId = session.getUser().getId();
        this.signature = session.getSignature();
    }

    public SessionDTO clone() {
        try {
            return (SessionDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

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
