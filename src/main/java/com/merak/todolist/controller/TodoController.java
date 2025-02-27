package com.merak.todolist.controller;

import com.merak.todolist.domain.dto.PageResponseDTO;
import com.merak.todolist.domain.entity.Todo;
import com.merak.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todos")
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<Todo>> getTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean isCompleted) {
        log.info("获取待办事项列表请求 - 页码: {}, 每页数量: {}, 是否完成: {}", page, size, isCompleted);
        try {
            List<Todo> todos = todoService.getTodosWithPagination(page, size, isCompleted);
            long total = todoService.getTotalCount(isCompleted);
            log.info("成功获取待办事项列表 - 总数: {}", total);
            return ResponseEntity.ok(PageResponseDTO.of(todos, total, page, size));
        } catch (Exception e) {
            log.error("获取待办事项列表失败", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        log.info("获取待办事项详情请求 - ID: {}", id);
        try {
            Todo todo = todoService.getTodoById(id);
            log.info("成功获取待办事项详情 - ID: {}", id);
            return ResponseEntity.ok(todo);
        } catch (Exception e) {
            log.error("获取待办事项详情失败 - ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        log.info("创建待办事项请求 - 内容: {}", todo);
        if (todo.getValue() == null || todo.getValue().trim().isEmpty()) {
            log.warn("创建待办事项失败 - 内容为空");
            return ResponseEntity.badRequest().build();
        }
        try {
            Todo newTodo = todoService.addTodo(todo);
            log.info("成功创建待办事项 - ID: {}", newTodo.getId());
            return ResponseEntity.ok(newTodo);
        } catch (Exception e) {
            log.error("创建待办事项失败", e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Long id) {
        log.info("更新待办事项状态请求 - ID: {}", id);
        try {
            Todo updatedTodo = todoService.updateTodoStatus(id);
            log.info("成功更新待办事项状态 - ID: {}, 新状态: {}", id, updatedTodo.getIsCompleted());
            return ResponseEntity.ok(updatedTodo);
        } catch (Exception e) {
            log.error("更新待办事项状态失败 - ID: {}", id, e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        log.info("删除待办事项请求 - ID: {}", id);
        try {
            boolean deleted = todoService.deleteTodo(id);
            if (deleted) {
                log.info("成功删除待办事项 - ID: {}", id);
                return ResponseEntity.noContent().build();
            } else {
                log.warn("删除待办事项失败 - ID: {} 不存在", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("删除待办事项失败 - ID: {}", id, e);
            throw e;
        }
    }
}