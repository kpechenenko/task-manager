package ru.kpechenenko.task.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public final class TaskManagerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<?> handleNoSuchElementException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
