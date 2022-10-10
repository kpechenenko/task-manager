package ru.kpechenenko.task.manager.service;

import ru.kpechenenko.task.manager.dto.TagDto;
import ru.kpechenenko.task.manager.dto.TaskDto;

import java.util.List;

public interface TaskManagerService {
    void createTask(TaskDto taskDto);

    List<TaskDto> findAllTasks();

    void deleteTask(Integer taskId);

    TagDto createTag(TagDto tagDto);

    TagDto findTagWithTasks(Integer tagId);

    void deleteTagWithTasks(Integer tagId);
}
