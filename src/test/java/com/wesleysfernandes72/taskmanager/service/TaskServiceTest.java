package com.wesleysfernandes72.taskmanager.service;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.model.TaskStatus;
import com.wesleysfernandes72.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    TaskService service;

    @Test
    void shouldCreateTask() {
        TaskRequest task = new TaskRequest("Estudar Spring", TaskStatus.PENDING, 3);

        TaskModel entity = new TaskModel();
        entity.setTitle("Estudar Spring");
        entity.setStatus(TaskStatus.PENDING);
        entity.setPriority(3);

        when(repository.save(any(TaskModel.class))).thenReturn(entity);

        TaskResponse result = service.create(task);

        assertEquals("Estudar Spring", result.title());
    }

    @Test
    void shouldGetTaskById() {
        Integer id = 7;



    }
}
