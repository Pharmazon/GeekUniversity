package ru.shcheglov.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.User;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String id;

    private String login;

    private String firstName;

    private String lastName;

    public UserDTO(@Nullable final User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public static UserDTO toDTO(final User user) {
        if (user == null) return null;
        return new UserDTO(user);
    }

}
