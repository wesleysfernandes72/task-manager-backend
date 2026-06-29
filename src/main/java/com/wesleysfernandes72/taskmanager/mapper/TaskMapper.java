package com.wesleysfernandes72.taskmanager.mapper;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskModel toEntity(TaskRequest request) {

        TaskModel task = new TaskModel();

        task.setTitle(request.title());
        task.setStatus(request.status());
        task.setPriority(request.priority());

        return task;
    }

    public TaskResponse toResponse(TaskModel task) {

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt()
        );
    }

    public void update(TaskRequest request, TaskModel task) {

        task.setTitle(request.title());
        task.setStatus(request.status());
        task.setPriority(request.priority());

    }
}