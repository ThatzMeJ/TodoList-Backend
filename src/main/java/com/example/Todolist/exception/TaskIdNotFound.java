package com.example.Todolist.exception;

public class TaskIdNotFound extends RuntimeException{

    public TaskIdNotFound(String message){
        super(message);
    }
}
