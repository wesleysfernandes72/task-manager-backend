package com.wesleysfernandes72.taskmanager.service;

import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.model.TaskStatus;
import com.wesleysfernandes72.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    void shouldFindAllTasksWithStatusFilter() {
        TaskModel task = new TaskModel();
        task.setId(1L);
        task.setTitle("Estudar Spring");
        task.setStatus(TaskStatus.PENDING);
        task.setPriority(3);
        task.setCreatedAt(LocalDateTime.of(2026, 6, 27, 10, 0));

        Page<TaskModel> page = new PageImpl<>(List.of(task));

        when(repository.findAll(
                any(Specification.class),
                any(Pageable.class)
        )).thenReturn(page);

        Page<TaskResponse> result = service.findAll(
                new TaskSearchRequest(TaskStatus.PENDING, null),
                PageRequest.of(0, 10)
        );

        assertEquals(1, result.getTotalElements());

        TaskResponse response = result.getContent().getFirst();

        assertEquals(TaskStatus.PENDING, response.status());
        assertEquals("Estudar Spring", response.title());
    }

    @Test
    void shouldFindAllTasksWithPriorityFilter() {
        TaskModel task = new TaskModel();
        task.setId(2L);
        task.setTitle("Finalizar API");
        task.setStatus(TaskStatus.DONE);
        task.setPriority(5);
        task.setCreatedAt(LocalDateTime.of(2026, 6, 27, 11, 0));

        Page<TaskModel> page = new PageImpl<>(List.of(task));

        when(repository.findAll(
                any(Specification.class),
                any(Pageable.class)
        )).thenReturn(page);

        Page<TaskResponse> result = service.findAll(
                new TaskSearchRequest(TaskStatus.PENDING, null),
                PageRequest.of(0, 10)
        );

        assertEquals(1, result.getTotalElements());

        TaskResponse response = result.getContent().getFirst();

        assertEquals(TaskStatus.PENDING, response.status());
        assertEquals("Estudar Spring", response.title());
    }
}
