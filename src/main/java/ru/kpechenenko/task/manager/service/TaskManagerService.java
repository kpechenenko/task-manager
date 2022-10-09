package ru.kpechenenko.task.manager.service;

import ru.kpechenenko.task.manager.dto.TagInput;
import ru.kpechenenko.task.manager.dto.TaskInput;

import java.util.List;

public interface TaskManagerService {
    void createTask(TaskInput newTask);

    List<TaskInput> findAllTasks();

    void deleteTask(Integer taskId);

    void createTag(TagInput newTag);

    TagInput findTagWithTasks(Integer tagId);

    void deleteTagWithTasks(Integer tagId);
}
