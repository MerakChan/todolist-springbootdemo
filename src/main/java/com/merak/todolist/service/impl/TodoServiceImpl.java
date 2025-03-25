package com.merak.todolist.service.impl;

import com.merak.todolist.domain.entity.Todo;
import com.merak.todolist.mapper.TodoMapper;
import com.merak.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;

    @Override
    public List<Todo> getAllTodos() {
        log.debug("获取所有待办事项");
        List<Todo> todos = todoMapper.findAll();
        log.debug("成功获取所有待办事项，总数: {}", todos.size());
        return todos;
    }

    @Override
    public List<Todo> getTodosWithPagination(int page, int size, Boolean isCompleted) {
        log.debug("分页获取待办事项 - 页码: {}, 每页数量: {}, 是否完成: {}", page, size, isCompleted);
        int offset = page * size;
        List<Todo> todos = todoMapper.findAllWithPagination(offset, size, isCompleted);
        log.debug("成功获取分页待办事项，结果数量: {}", todos.size());
        return todos;
    }

    @Override
    public long getTotalCount(Boolean isCompleted) {
        return todoMapper.getTotalCount(isCompleted);
    }

    @Override
    public Todo addTodo(Todo todo) {
        log.debug("添加待办事项 - 内容: {}", todo);
        if (todo.getIsCompleted() == null) {
            todo.setIsCompleted(false);
            log.debug("设置默认完成状态为false");
        }
        todoMapper.insert(todo);
        log.debug("成功添加待办事项 - ID: {}", todo.getId());
        return todo;
    }

    @Override
    public List<Todo> addTodos(List<Todo> todos) {
        todos.forEach(todo -> {
            if (todo.getIsCompleted() == null) {
                todo.setIsCompleted(false);
            }
        });
        todoMapper.batchInsert(todos);
        return todos;
    }

    @Override
    public Todo updateTodoStatus(Long id) {
        log.debug("更新待办事项状态 - ID: {}", id);
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            log.error("待办事项不存在 - ID: {}", id);
            throw new RuntimeException("Todo item not found with id: " + id);
        }
        todo.setIsCompleted(!todo.getIsCompleted());
        todoMapper.updateStatus(todo);
        log.debug("成功更新待办事项状态 - ID: {}, 新状态: {}", id, todo.getIsCompleted());
        return todo;
    }

    @Override
    public List<Todo> updateTodosStatus(List<Long> ids, boolean isCompleted) {
        List<Todo> todos = ids.stream()
            .map(id -> {
                Todo todo = todoMapper.findById(id);
                if (todo == null) {
                    throw new RuntimeException("Todo item not found with id: " + id);
                }
                todo.setIsCompleted(isCompleted);
                return todo;
            })
            .collect(Collectors.toList());
        
        todoMapper.batchUpdateStatus(todos);
        return todos;
    }

    @Override
    public boolean deleteTodo(Long id) {
        log.debug("删除待办事项 - ID: {}", id);
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            log.error("待办事项不存在 - ID: {}", id);
            throw new RuntimeException("Todo item not found with id: " + id);
        }
        boolean result = todoMapper.deleteById(id) > 0;
        if (result) {
            todoMapper.resetAutoIncrement();
            log.debug("重置自增ID成功");
        }
        log.debug("删除待办事项结果 - ID: {}, 是否成功: {}", id, result);
        return result;
    }

    @Override
    public Todo getTodoById(Long id) {
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            throw new RuntimeException("Todo item not found with id: " + id);
        }
        return todo;
    }
}