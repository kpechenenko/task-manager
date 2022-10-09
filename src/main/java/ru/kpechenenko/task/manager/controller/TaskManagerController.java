package ru.kpechenenko.task.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpechenenko.task.manager.dto.TagInput;
import ru.kpechenenko.task.manager.dto.TaskInput;
import ru.kpechenenko.task.manager.service.TaskManagerService;

@RestController
@RequiredArgsConstructor
public final class TaskManagerController {
    private final TaskManagerService service;

    private static final String TASK_RESOURCE = "/tasks";
    private static final String TAG_RESOURCE = "/tags";

    @PostMapping(TASK_RESOURCE)
    public ResponseEntity<?> addNewTask(@RequestBody TaskInput newTask) {
        this.service.createTask(newTask);
        return ResponseEntity.ok().build();
    }

    @GetMapping(TASK_RESOURCE)
    public ResponseEntity<?> getAllTask() {
        return ResponseEntity.ok(this.service.findAllTasks());
    }


    @DeleteMapping(TASK_RESOURCE + "/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer taskId) {
        this.service.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(TAG_RESOURCE)
    public ResponseEntity<?> addNewTag(@RequestBody TagInput newTag) {
        this.service.createTag(newTag);
        return ResponseEntity.ok().build();
    }

    @GetMapping(TAG_RESOURCE + "/{tagId}")
    public ResponseEntity<?> getTagWithTasks(@PathVariable Integer tagId) {
        return ResponseEntity.ok(this.service.findTagWithTasks(tagId));
    }

    @DeleteMapping(TAG_RESOURCE + "/{tagId}")
    public ResponseEntity<?> deleteTagWithTasks(@PathVariable Integer tagId) {
        this.service.deleteTagWithTasks(tagId);
        return ResponseEntity.noContent().build();
    }
}