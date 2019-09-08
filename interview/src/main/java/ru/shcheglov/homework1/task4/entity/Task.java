package ru.shcheglov.homework1.task4.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Task implements Entity {

    private int id;

    @NonNull
    private String name;

    private String description;

    private Project project;

    @Override
    public String toString() {
        return "Project [" + project.getId() + ". " + project.getName() + "] task={" + id + ". " + name + "}";
    }
}
