package com.wesleysfernandes72.taskmanager.repository;

import com.wesleysfernandes72.taskmanager.model.TaskModel;
import com.wesleysfernandes72.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByStatus(TaskStatus status);
}
