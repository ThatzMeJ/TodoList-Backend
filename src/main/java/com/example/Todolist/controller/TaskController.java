package com.example.Todolist.controller;

import com.example.Todolist.entity.Task;
import com.example.Todolist.entity.TaskList;
import com.example.Todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasklist/{tasklistId}")
    public ResponseEntity<List<Task>> getAllTaskByTaskListId(@PathVariable("tasklistId") Long tasklistId){
        return ResponseEntity.ok(taskService.getAllTaskByTaskListId(tasklistId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @PostMapping("/tasklist/{tasklistid}/new")
    public ResponseEntity<?> createNewTask(@PathVariable("tasklistid") Long tasklistId, @RequestBody Task task){
        return ResponseEntity.ok(taskService.createNewTask(tasklistId, task));
    }


    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("taskId") Long taskId){
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok("Task " + taskId + " was successfully deleted.");
    }

    @DeleteMapping("/delete/{taskListId}/all")
    public ResponseEntity<TaskList> deleteAllTaskByTaskListId(@PathVariable("taskListId") Long taskListId){
        TaskList updatedTaskList = taskService.deleteAllTasksOfTaskList(taskListId);
        return ResponseEntity.ok(updatedTaskList);
    }
}
