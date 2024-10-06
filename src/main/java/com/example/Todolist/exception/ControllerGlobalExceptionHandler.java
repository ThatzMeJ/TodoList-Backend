package com.example.Todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGlobalExceptionHandler {

    @ExceptionHandler(TaskListNotFound.class)
    public ResponseEntity<String> handleTaskListNotFound(TaskListNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TaskIdNotFound.class)
    public ResponseEntity<String> handleTaskIdNotFound(TaskIdNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
