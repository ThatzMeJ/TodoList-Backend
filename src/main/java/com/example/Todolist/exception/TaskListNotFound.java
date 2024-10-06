package com.example.Todolist.exception;

public class TaskListNotFound extends RuntimeException{

    public TaskListNotFound(String message){
        super(message);
    }
}
