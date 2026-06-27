package com.wesleysfernandes72.taskmanager.repository;

import com.wesleysfernandes72.taskmanager.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long>, JpaSpecificationExecutor<TaskModel> {
}
