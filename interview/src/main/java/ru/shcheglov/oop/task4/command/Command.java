package ru.shcheglov.homework1.task4.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Command {

    PROJECT_CREATE("project-create", "Add new project"),
    PROJECT_CLEAR("project-clear", "Remove all projects"),
    PROJECT_LIST("project-list", "Show all projects"),
    PROJECT_REMOVE("project-remove", "Remove project by id"),
    TASK_CREATE("task-create", "Add new task by project"),
    TASK_CLEAR("task-clear", "Remove all tasks by project"),
    TASK_LIST("task-list", "Show task list by project"),
    TASK_REMOVE("task-remove", "Remove the task from project"),
    HELP("help", "Show commands description"),
    QUIT("quit", "Quit the program");

    private String command;

    private String description;

}
