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
        if (null != taskDto.getId()) {
            this.updateTask(taskDto);
            return;
        }
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

    private void updateTask(TaskDto taskDto) {
        var taskToUpdate = this.taskRepository.findById(taskDto.getId()).orElseThrow(
            () -> new NoSuchElementException("Task with id #%d to update does not exists!".formatted(taskDto.getTagId()))
        );
        if (!taskToUpdate.getTitle().equals(taskDto.getName())) {
            taskToUpdate.setTitle(taskDto.getName());
        }
        if (!taskToUpdate.getDescription().equals(taskDto.getDescription())) {
            taskToUpdate.setDescription(taskDto.getDescription());
        }
        if (!taskToUpdate.getDeadLine().equals(taskDto.getDeadline())) {
            taskToUpdate.setDeadLine(taskDto.getDeadline());
        }
        if (!taskToUpdate.getTag().getId().equals(taskDto.getId())) {
            var newTaskTag = this.tagRepository.findById(taskDto.getTagId()).orElseThrow(
                () -> new NoSuchElementException(
                    "Task with id #%d to update task with id #%d does not exists!".formatted(
                        taskDto.getTagId(),
                        taskToUpdate.getId()
                    )
                )
            );
            var currentTaskTag = taskToUpdate.getTag();
            currentTaskTag.removeTask(taskToUpdate);
            newTaskTag.addTask(taskToUpdate);
            this.tagRepository.saveAll(List.of(currentTaskTag, newTaskTag));
        }
        this.taskRepository.save(taskToUpdate);
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
    public TagDto createTag(TagDto tagDto) {
        if (null != tagDto.getId()) {
            return this.updateTag(tagDto);
        }
        var newTag = new Tag(tagDto.getName());
        return TagDto.fromEntity(this.tagRepository.save(newTag));
    }

    private TagDto updateTag(TagDto tagDto) {
        var tagToUpdate = this.tagRepository.findById(tagDto.getId()).orElseThrow(
            () -> new NoSuchElementException("Tag with id #%d to update does not exists!".formatted(tagDto.getId()))
        );
        if (!tagToUpdate.getName().equals(tagDto.getName())) {
            tagToUpdate.setName(tagDto.getName());
        }
        return TagDto.fromEntity(this.tagRepository.save(tagToUpdate));
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
