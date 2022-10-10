package ru.kpechenenko.task.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpechenenko.task.manager.dto.ResponseModel;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel<?> addNewTask(@RequestBody TaskDto newTask) {
        return new ResponseModel<>(
            HttpStatus.CREATED.value(),
            this.service.createTask(newTask)
        );
    }

    @GetMapping(TASK_RESOURCE)
    public ResponseModel<?> getAllTask() {
        return new ResponseModel<>(
            HttpStatus.OK.value(),
            this.service.findAllTasks()
        );
    }


    @DeleteMapping(TASK_RESOURCE + "/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer taskId) {
        this.service.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(TAG_RESOURCE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel<?> addNewTag(@RequestBody TagDto tagDto) {
        return new ResponseModel<>(
            HttpStatus.CREATED.value(),
            this.service.createTag(tagDto)
        );
    }

    @GetMapping(TAG_RESOURCE + "/{tagId}")
    public ResponseModel<?> getTagWithTasks(@PathVariable Integer tagId) {
        return new ResponseModel<>(
            HttpStatus.OK.value(),
            this.service.findTagWithTasks(tagId)
        );
    }

    @DeleteMapping(TAG_RESOURCE + "/{tagId}")
    public ResponseEntity<?> deleteTagWithTasks(@PathVariable Integer tagId) {
        this.service.deleteTagWithTasks(tagId);
        return ResponseEntity.noContent().build();
    }
}
