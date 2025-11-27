package com.example.service;

import com.example.bean.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) throws Exception {
        
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) throws Exception {
        if (!taskRepository.existsById(task.getId())) {
            throw new Exception("Task not found");
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer id) throws Exception {
        if (!taskRepository.existsById(id)) {
            throw new Exception("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findAll()
            .stream()
            .filter(task -> task.getStatus() != null && 
                task.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findAll()
            .stream()
            .filter(task -> task.getPriority() != null && 
                task.getPriority().equalsIgnoreCase(priority))
            .collect(Collectors.toList());
    }

    @Override
    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        return taskRepository.findAll()
            .stream()
            .filter(task -> task.getDueDate() != null &&
                task.getDueDate().isBefore(today) &&
                !task.getStatus().equalsIgnoreCase("completed"))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }
}
