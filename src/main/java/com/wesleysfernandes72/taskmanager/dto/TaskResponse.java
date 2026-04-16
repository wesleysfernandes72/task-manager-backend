package com.wesleysfernandes72.taskmanager.dto;

import com.wesleysfernandes72.taskmanager.model.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        TaskStatus status,
        Integer priority,
        LocalDateTime createdAt
) {
}
