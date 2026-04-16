package com.wesleysfernandes72.taskmanager.dto;

import com.wesleysfernandes72.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TaskRequest(

        @NotBlank
        String title,

        TaskStatus status,

        @Min(1)
        @Max(5)
        Integer priority
) {
}
