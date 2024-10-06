package com.example.Todolist.service;


import com.example.Todolist.entity.Task;
import com.example.Todolist.entity.TaskList;
import com.example.Todolist.exception.TaskListNotFound;
import com.example.Todolist.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    // Get Methods
    public List<TaskList> getAllTaskLists(){
        return taskListRepository.findAll();
    }

    public TaskList getTaskListById(Long id){
        return taskListRepository.findById(id)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + id + " not found."));
    }

    public List<Task> getTasksByTaskListId(Long taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + taskListId + " not found."));
        return taskList.getTasks();  // Return the list of tasks for that TaskList
    }

    //Returns total tasks for that taskList
    public int getTaskListSize(Long _id){
        TaskList taskList = taskListRepository.findById(_id)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + _id + " not found."));
        return taskList.getTasks().size();
    }

    //Post Methods
    public TaskList createTaskList(TaskList taskList){

        return taskListRepository.save(taskList);
    }

    // Delete Methods
    public void deleteTaskListById(Long id){
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + id + " not found."));
        taskListRepository.delete(taskList);
    }

    public void deleteTaskList(TaskList taskList){
        if (taskListRepository.existsById(taskList.getId())) {
            taskListRepository.delete(taskList);
        } else {
            throw new TaskListNotFound("TaskList with id " + taskList.getId() + " not found.");
        }
    }

    public void deleteAllTaskList(){
        taskListRepository.deleteAll();
    }

}
