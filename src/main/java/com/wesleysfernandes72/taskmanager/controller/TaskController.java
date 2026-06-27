package com.wesleysfernandes72.taskmanager.controller;


import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request) {
        TaskResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(@Valid TaskSearchRequest request) {
        return ResponseEntity.ok(service.findAll(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update (
            @PathVariable Long id,
            @RequestBody @Valid TaskRequest request
    ) {
        return  ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
