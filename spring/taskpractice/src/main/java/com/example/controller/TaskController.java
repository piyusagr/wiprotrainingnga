package com.example.controller;

import com.example.bean.Task;
import com.example.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@Valid @RequestBody Task task) {
        try {
            taskService.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully.");
        } catch (Exception e) {
            if (e.getMessage().contains("exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Task with same ID already exists.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@Valid @RequestBody Task task) {
        try {
            taskService.updateTask(task);
            return ResponseEntity.ok("Successfully updated.");
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task does not exist.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task doesn't exist.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTasksByStatus(@RequestParam(required = false) String status) {
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().body("Status parameter is required.");
        }
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> tasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(tasks);
    }
}
