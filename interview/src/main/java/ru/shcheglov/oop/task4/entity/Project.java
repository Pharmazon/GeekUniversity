package ru.shcheglov.homework1.task4.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Project implements Entity {

    private int id;

    @NonNull
    private String name;

    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
