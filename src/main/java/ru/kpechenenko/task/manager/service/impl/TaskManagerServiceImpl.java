package ru.kpechenenko.task.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpechenenko.task.manager.dto.TagDto;
import ru.kpechenenko.task.manager.dto.TaskDto;
import ru.kpechenenko.task.manager.entity.Tag;
import ru.kpechenenko.task.manager.entity.Task;
import ru.kpechenenko.task.manager.repository.TagRepository;
import ru.kpechenenko.task.manager.repository.TaskRepository;
import ru.kpechenenko.task.manager.service.TaskManagerService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskManagerServiceImpl implements TaskManagerService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    @Override
    public void createTask(TaskDto taskDto) {
        var ownedTag = this.tagRepository.findById(taskDto.getTagId()).orElseThrow(
            () -> new NoSuchElementException("Tag with id #%d does not exists!".formatted(taskDto.getTagId()))
        );
        var newTask = new Task(
            taskDto.getName(),
            taskDto.getDescription(),
            taskDto.getDeadline(),
            ownedTag
        );
        ownedTag.addTask(newTask);
        this.tagRepository.save(ownedTag);
    }

    @Override
    public List<TaskDto> findAllTasks() {
        return this.taskRepository.findAll().stream()
            .map(TaskDto::fromEntity)
            .toList();
    }

    @Override
    public void deleteTask(Integer taskId) {
        var deletedTask = this.taskRepository.findById(taskId).orElseThrow(
            () -> new NoSuchElementException("Task with id #%d does not exists!".formatted(taskId))
        );
        deletedTask.getTag().removeTask(deletedTask);
        this.taskRepository.delete(deletedTask);
    }

    @Override
    public void createTag(TagDto tagDto) {
        var newTag = new Tag(tagDto.getName());
        this.tagRepository.save(newTag);
    }

    @Override
    public TagDto findTagWithTasks(Integer tagId) {
        var tag = this.tagRepository.findById(tagId).orElseThrow(
            () -> new NoSuchElementException("Tag with id #%d does not exists!".formatted(tagId))
        );
        return TagDto.fromEntity(tag);
    }

    @Transactional
    @Override
    public void deleteTagWithTasks(Integer tagId) {
        if (!this.tagRepository.existsById(tagId)) {
            throw new NoSuchElementException("Tag with id #%d does not exists!".formatted(tagId));
        }
        this.tagRepository.deleteById(tagId);
    }
}
