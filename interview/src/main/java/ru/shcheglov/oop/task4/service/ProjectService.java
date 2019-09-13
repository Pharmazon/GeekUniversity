package ru.shcheglov.homework1.task4.service;

import lombok.AllArgsConstructor;
import ru.shcheglov.homework1.task4.entity.Project;
import ru.shcheglov.homework1.task4.entity.Task;

import java.util.List;

@AllArgsConstructor
public class ProjectService extends AbstractService<Project> {

    private TaskService taskService;

    public void addTask(int projectId, Task task) {
        Project project = super.getById(projectId);
        task.setProject(project);
        taskService.add(task);
        project.getTasks().add(task);
    }

    public void removeTaskByProjectAndTaskId(int projectId, int taskId) {
        Project project = super.getById(projectId);
        project.getTasks().remove(taskId);
    }

    public void removeAllTasksByProjectId(int projectId) {
        Project project = super.getById(projectId);
        project.getTasks().clear();
    }

    public List<Task> getAllTasksByProjectId(int projectId) {
        Project project = super.getById(projectId);
        return project.getTasks();
    }
}
