package ru.kpechenenko.task.manager.service;

import ru.kpechenenko.task.manager.dto.TagDto;
import ru.kpechenenko.task.manager.dto.TaskInput;

import java.util.List;

public interface TaskManagerService {
    void createTask(TaskInput newTask);

    List<TaskInput> findAllTasks();

    void deleteTask(Integer taskId);

    void createTag(TagDto tagDto);

    TagDto findTagWithTasks(Integer tagId);

    void deleteTagWithTasks(Integer tagId);
}
