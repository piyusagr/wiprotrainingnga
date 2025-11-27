package com.example.service;

import com.example.bean.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task addTask(Task task) throws Exception;
    Task updateTask(Task task) throws Exception;
    void deleteTask(Integer id) throws Exception;
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByPriority(String priority);
    List<Task> getOverdueTasks();
    Optional<Task> getTaskById(Integer id);
}
