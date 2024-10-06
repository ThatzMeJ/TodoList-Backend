package com.example.Todolist.controller;

import com.example.Todolist.entity.TaskList;
import com.example.Todolist.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasklist")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService){
        this.taskListService = taskListService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskList>> getAllTaskList(){
        return ResponseEntity.ok(taskListService.getAllTaskLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable("id") Long id){
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @GetMapping("/size/{id}")
    public ResponseEntity<Integer> getTaskListTotalTaskNumber(@PathVariable("id") Long _id){
        return ResponseEntity.ok(taskListService.getTaskListSize(_id));
    }

    @PostMapping("/new")
    public ResponseEntity<TaskList> createNewTaskList(@RequestBody TaskList taskList){
        TaskList newTaskList = taskListService.createTaskList(taskList);
        return ResponseEntity.ok(newTaskList);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllTaskList(){
        taskListService.deleteAllTaskList();
        return new ResponseEntity<>("All Tasklist deleted successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskListById(@PathVariable("id") Long id){
        taskListService.deleteTaskListById(id);
        return new ResponseEntity<>("Tasklist successfully deleted.", HttpStatus.OK);
    }
}
