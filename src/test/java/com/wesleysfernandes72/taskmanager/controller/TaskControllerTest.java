package com.wesleysfernandes72.taskmanager.controller;

import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.model.TaskStatus;
import com.wesleysfernandes72.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    TaskService service;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TaskController(service)).build();
    }

    @Test
    void shouldBindQueryParamsToTaskSearchRequest() throws Exception {
        when(service.findAll(any())).thenReturn(List.of(
                new TaskResponse(1L, "Estudar Spring", TaskStatus.PENDING, 3, LocalDateTime.of(2026, 6, 27, 10, 0))
        ));

        mockMvc.perform(get("/tasks")
                        .param("status", "PENDING")
                        .param("priority", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Estudar Spring"))
                .andExpect(jsonPath("$[0].status").value("PENDING"))
                .andExpect(jsonPath("$[0].priority").value(3));
    }
}
