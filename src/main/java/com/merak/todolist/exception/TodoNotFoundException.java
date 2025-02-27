package com.merak.todolist.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(Long id) {
        super("Todo item not found with id: " + id);
    }
}