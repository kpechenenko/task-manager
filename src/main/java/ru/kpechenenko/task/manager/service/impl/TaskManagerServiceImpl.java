package ru.kpechenenko.task.manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpechenenko.task.manager.dto.TagDto;
import ru.kpechenenko.task.manager.dto.TaskInput;
import ru.kpechenenko.task.manager.entity.Tag;
import ru.kpechenenko.task.manager.entity.Task;
import ru.kpechenenko.task.manager.repository.TagRepository;
import ru.kpechenenko.task.manager.repository.TaskRepository;
import ru.kpechenenko.task.manager.service.TaskManagerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskManagerServiceImpl implements TaskManagerService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    @Override
    public void createTask(TaskInput taskInput) {
        var ownedTag = this.tagRepository.findById(taskInput.getTagId()).orElseThrow(
            () -> new IllegalArgumentException("Tag with id #%d does not exists!".formatted(taskInput.getTagId()))
        );
        var newTask = new Task(
            taskInput.getName(),
            taskInput.getDescription(),
            taskInput.getDeadline(),
            ownedTag
        );
        ownedTag.addTask(newTask);
        this.tagRepository.save(ownedTag);
    }

    @Override
    public List<TaskInput> findAllTasks() {
        // todo extract dto to entity to method
        return this.taskRepository.findAll().stream()
            .map(t -> new TaskInput(
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getDeadLine(),
                    t.getTag().getId()
                )
            ).toList();
    }

    @Override
    public void deleteTask(Integer taskId) {
        var deletedTask = this.taskRepository.findById(taskId).orElseThrow(
            () -> new IllegalArgumentException("Task with id #%d does not exists!".formatted(taskId))
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
            () -> new IllegalArgumentException("Tag with id #%d does not exists!".formatted(tagId))
        );
        // todo extract dto to entity to method
        return new TagDto(
            tag.getId(),
            tag.getName(),
            tag.getTasks().stream()
                .map(t -> new TaskInput(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getDeadLine(),
                        t.getTag().getId()
                    )
                ).toList()
        );
    }

    @Transactional
    @Override
    public void deleteTagWithTasks(Integer tagId) {
        if (!this.tagRepository.existsById(tagId)) {
            throw new IllegalArgumentException("Tag with id #%d does not exists!".formatted(tagId));
        }
        this.tagRepository.deleteById(tagId);
    }
}
