package com.wesleysfernandes72.taskmanager.service;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.exception.TaskNotFoundException;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.repository.TaskRepository;
import com.wesleysfernandes72.taskmanager.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class TaskService {

    private final TaskRepository repository;

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

    public Page<TaskResponse> findAll(
            TaskSearchRequest request,
            Pageable pageable
    ) {

        return repository
                .findAll(TaskSpecification.byFilter(request), pageable)
                .map(this::toResponse);
    }

    public TaskResponse findById(Long id) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return toResponse(task);
    }

    public TaskResponse update(Long id, TaskRequest dto) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(dto.title());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());

        return toResponse(repository.save(task));
    }

    public void delete(Long id) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.delete(task);
    }
}
