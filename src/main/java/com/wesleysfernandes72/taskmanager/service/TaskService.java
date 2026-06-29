package com.wesleysfernandes72.taskmanager.service;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.exception.TaskNotFoundException;
import com.wesleysfernandes72.taskmanager.mapper.TaskMapper;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.repository.TaskRepository;
import com.wesleysfernandes72.taskmanager.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskResponse create(TaskRequest request) {

        TaskModel task = mapper.toEntity(request);

        TaskModel saved = repository.save(task);

        return mapper.toResponse(saved);
    }

    public Page<TaskResponse> findAll(
            TaskSearchRequest request,
            Pageable pageable
    ) {

        return repository
                .findAll(TaskSpecification.byFilter(request), pageable)
                .map(mapper::toResponse);
    }

    public TaskResponse findById(Long id) {

        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapper.toResponse(task);
    }

    public TaskResponse update(Long id, TaskRequest request) {

        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        mapper.update(request, task);

        TaskModel updated = repository.save(task);

        return mapper.toResponse(updated);
    }

    public void delete(Long id) {

        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        repository.delete(task);
    }
}