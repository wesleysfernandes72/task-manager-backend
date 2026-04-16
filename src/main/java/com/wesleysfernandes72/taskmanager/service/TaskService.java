package com.wesleysfernandes72.taskmanager.service;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository respository) {
        this.repository = respository;
    }

    private TaskResponse toResponse(TaskModel task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt()
        );
    }

    public TaskResponse create(TaskRequest dto) {
        TaskModel task = new TaskModel();
        task.setTitle(dto.title());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());

        TaskModel saved = repository.save(task);

        return toResponse(saved);
    }

    public List<TaskResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse findById(Long id) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        return toResponse(task);
    }

    public TaskResponse update(Long id, TaskRequest dto) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        task.setTitle(dto.title());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());

        return toResponse(repository.save(task));
    }

    public void delete(Long id) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        repository.delete(task);
    }
}
