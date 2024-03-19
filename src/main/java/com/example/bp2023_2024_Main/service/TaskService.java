package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.entity.Project_Task;
import com.example.bp2023_2024_Main.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Project_Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Project_Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Project_Task createTask(Project_Task task) {
        return taskRepository.save(task);
    }

    public Project_Task updateTask(Project_Task updatedtask,Long id) {
        Project_Task existingTask = new Project_Task();
        existingTask = taskRepository.findById(id).orElseThrow();
        existingTask.setTaskName(updatedtask.getTaskName());
        existingTask.setDepartment(updatedtask.getDepartment());
        return taskRepository.save(existingTask);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
