package ru.kpechenenko.task.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpechenenko.task.manager.dto.TagDto;
import ru.kpechenenko.task.manager.dto.TaskDto;
import ru.kpechenenko.task.manager.service.TaskManagerService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/task-manager/api")
public final class TaskManagerController {
    private final TaskManagerService service;

    private static final String TASK_RESOURCE = "/tasks";
    private static final String TAG_RESOURCE = "/tags";

    @PostMapping(TASK_RESOURCE)
    public ResponseEntity<?> addNewTask(@RequestBody TaskDto newTask) {
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
    public ResponseEntity<?> addNewTag(@RequestBody TagDto tagDto) {
        this.service.createTag(tagDto);
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
