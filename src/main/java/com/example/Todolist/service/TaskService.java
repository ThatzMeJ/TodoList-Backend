package com.example.Todolist.service;

import com.example.Todolist.entity.Task;
import com.example.Todolist.entity.TaskList;
import com.example.Todolist.exception.TaskIdNotFound;
import com.example.Todolist.exception.TaskListNotFound;
import com.example.Todolist.repository.TaskListRepository;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskListRepository taskListRepository){
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    //GET Methods
    public List<Task> getAllTaskByTaskListId(Long taskListId){
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + taskListId + " not found."));
        List<Task> tasks = taskList.getTasks();
        return tasks;
    }

    public Task getTaskById(Long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskIdNotFound("Task was not found"));

        return task;
    }

    //POST Methods
    public Task createNewTask(Long taskListId, Task task){
        // Find the existing TaskList by ID or throw an exception if not found
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + taskListId + " not found."));

        // Create a new Task with title, startTime, and endTime
        Task newTask = new Task(task.getTitle(), task.getStartTime(), task.getEndTime());

        // Associate the new Task with the found TaskList
        newTask.setTaskList(taskList);

        // Save and return the new Task
        return taskRepository.save(newTask);
    }

    //DELETE Methods

    public void deleteTaskById(Long taskId){
        if(!taskRepository.existsById(taskId)){
            throw new TaskIdNotFound("Task " + taskId + " was not found.");
        }
        taskRepository.deleteById(taskId);
    }

    public TaskList deleteAllTasksOfTaskList(Long taskListId) {
        // Fetch the TaskList by ID, or throw an exception if not found
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new TaskListNotFound("TaskList with id " + taskListId + " not found."));

        // Remove all tasks from the TaskList
        taskList.removeAllTasks();

        // Save the updated TaskList (tasks should be removed from the database)
        TaskList updatedTaskList = taskListRepository.save(taskList);

        // Return the updated TaskList (tasks should now be empty)
        return updatedTaskList;
    }


}
