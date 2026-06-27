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

        when(repository.findAll(any(Specification.class))).thenReturn(List.of(task));

        List<TaskResponse> result = service.findAll(new TaskSearchRequest(TaskStatus.PENDING, null));

        assertEquals(1, result.size());
        assertEquals(TaskStatus.PENDING, result.get(0).status());
        assertEquals("Estudar Spring", result.get(0).title());
    }

    @Test
    void shouldFindAllTasksWithPriorityFilter() {
        TaskModel task = new TaskModel();
        task.setId(2L);
        task.setTitle("Finalizar API");
        task.setStatus(TaskStatus.DONE);
        task.setPriority(5);
        task.setCreatedAt(LocalDateTime.of(2026, 6, 27, 11, 0));

        when(repository.findAll(any(Specification.class))).thenReturn(List.of(task));

        List<TaskResponse> result = service.findAll(new TaskSearchRequest(null, 5));

        assertEquals(1, result.size());
        assertEquals(5, result.get(0).priority());
        assertEquals(TaskStatus.DONE, result.get(0).status());
    }
}
