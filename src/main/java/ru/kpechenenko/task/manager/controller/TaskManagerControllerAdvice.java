package ru.kpechenenko.task.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kpechenenko.task.manager.dto.ResponseModel;

import java.util.NoSuchElementException;

@RestControllerAdvice
public final class TaskManagerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseModel<?> handleNoSuchElementException(Exception ex) {
        return new ResponseModel<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
