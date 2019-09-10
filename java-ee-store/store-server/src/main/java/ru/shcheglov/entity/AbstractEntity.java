package ru.shcheglov.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Nullable
    @Column(name = "name")
    private String name;

    public AbstractEntity(@Nullable final String name) {
        this.name = name;
    }
}
