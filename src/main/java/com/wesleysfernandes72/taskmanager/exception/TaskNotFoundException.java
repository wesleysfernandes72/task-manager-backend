package com.wesleysfernandes72.taskmanager.exception;

public class TaskNotFoundException extends DomainException {
    public TaskNotFoundException(Long id)
    {
        super("Task com id " + id + " não encontrada.");
    }
}
