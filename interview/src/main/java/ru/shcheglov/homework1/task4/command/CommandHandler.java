package ru.shcheglov.homework1.task4.command;

import ru.shcheglov.homework1.task4.entity.Project;
import ru.shcheglov.homework1.task4.entity.Task;
import ru.shcheglov.homework1.task4.message.Printer;
import ru.shcheglov.homework1.task4.service.ProjectService;
import ru.shcheglov.homework1.task4.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    private Scanner scanner = new Scanner(System.in);
    private ProjectService projectService = new ProjectService(new TaskService());
    private boolean isQuitRequested = false;

    public void execute() {
        Printer.printWelcome();
        while (!isQuitRequested) {
            Printer.printRequest("Enter command");
            String entered = scanner.next().trim().toLowerCase();
            Command command = validateCommand(entered);
            if (command == null) {
                Printer.printError("Wrong command!");
                continue;
            }
            handleUserEnter(command);
        }
    }

    private void handleUserEnter(Command command) {
        switch (command) {
            case PROJECT_CREATE:
                handleProjectCreate();
                break;
            case PROJECT_CLEAR:
                handleProjectClear();
                break;
            case PROJECT_LIST:
                handleProjectList();
                break;
            case PROJECT_REMOVE:
                handleProjectRemove();
                break;
            case TASK_CREATE:
                handleTaskCreate();
                break;
            case TASK_CLEAR:
                handleTaskClear();
                break;
            case TASK_LIST:
                handleTaskList();
                break;
            case TASK_REMOVE:
                handleTaskRemove();
                break;
            case HELP:
                Printer.printHelp();
                break;
            case QUIT:
                isQuitRequested = true;
                Printer.printByeBye();
                break;
        }
    }

    private void handleTaskRemove() {
        Printer.printRequest("Project id");
        int i = scanner.nextInt();
        Printer.printRequest("Task id");
        int j = scanner.nextInt();
        projectService.removeTaskByProjectAndTaskId(i, j);
        Printer.printSuccess("Task removed");
    }

    private void handleTaskClear() {
        handleProjectList();
        Printer.printRequest("Project id");
        projectService.removeAllTasksByProjectId(scanner.nextInt());
        Printer.printSuccess("Task cleared");
    }

    private void handleTaskCreate() {
        Printer.printRequest("Project id");
        int projectIndex = scanner.nextInt();
        Project project = projectService.getById(projectIndex);
        Printer.printRequest("Task name");
        String taskName = scanner.next().trim().toLowerCase();
        projectService.addTask(projectIndex, new Task(taskName));
        Printer.printSuccess("Task " + taskName + " added to project " + project);
    }

    private void handleProjectRemove() {
        handleProjectList();
        Printer.printRequest("Project id");
        int next = scanner.nextInt();
        Project project = projectService.getById(next);
        projectService.removeById(next);
        Printer.printSuccess(project + " removed.");
    }

    private void handleProjectClear() {
        projectService.removeAll();
        Printer.printSuccess("All projects cleared");
    }

    private void handleProjectCreate() {
        Printer.printRequest("Project name");
        String projectName = scanner.next().trim().toLowerCase();
        Project project = new Project(projectName);
        projectService.add(project);
        Printer.printSuccess(project + " added.");
    }

    private void handleProjectList() {
        List<Project> projects = projectService.getAll();
        if (projects.isEmpty()) {
            Printer.printSuccess("No projects in database");
            return;
        }
        projects.forEach(System.out::println);
        Printer.printSeparator();
    }

    private void handleTaskList() {
        Printer.printRequest("Project id");
        int projectId = scanner.nextInt();
        List<Task> tasks = projectService.getAllTasksByProjectId(projectId);
        if (tasks.isEmpty()) {
            Printer.printSuccess("No tasks in database");
            return;
        }
        tasks.forEach(System.out::println);
        Printer.printSeparator();
    }

    private Command validateCommand(String strCommand) {
        for (Command comm : Command.values()) {
            if (comm.getCommand().equals(strCommand)) return comm;
        }
        return null;
    }
}
