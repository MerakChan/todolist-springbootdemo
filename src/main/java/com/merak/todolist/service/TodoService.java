package com.merak.todolist.service;

import com.merak.todolist.domain.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    List<Todo> getTodosWithPagination(int page, int size, Boolean isCompleted);
    long getTotalCount(Boolean isCompleted);
    Todo addTodo(Todo todo);
    List<Todo> addTodos(List<Todo> todos);
    Todo updateTodoStatus(Long id);
    List<Todo> updateTodosStatus(List<Long> ids, boolean isCompleted);
    boolean deleteTodo(Long id);
    Todo getTodoById(Long id);
}