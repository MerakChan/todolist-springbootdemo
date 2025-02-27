//package com.example.todolist.controller;
//
//import com.example.todolist.domain.entity.Todo;
//import com.example.todolist.service.TodoService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import java.util.Arrays;
//import java.util.List;
//
//public class TodoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TodoService todoService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testGetAllTodos() throws Exception {
//        // 准备测试数据
//        Todo todo1 = new Todo();
//        todo1.setId(1L);
//        todo1.setValue("测试待办事项1");
//        todo1.setIsCompleted(false);
//
//        Todo todo2 = new Todo();
//        todo2.setId(2L);
//        todo2.setValue("测试待办事项2");
//        todo2.setIsCompleted(true);
//
//        List<Todo> todos = Arrays.asList(todo1, todo2);
//
//        // 模拟服务层行为
//        when(todoService.getAllTodos()).thenReturn(todos);
//
//        // 执行测试并验证结果
//        mockMvc.perform(get("/api/get-todo"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].value").value("测试待办事项1"))
//                .andExpect(jsonPath("$[0].isCompleted").value(false))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].value").value("测试待办事项2"))
//                .andExpect(jsonPath("$[1].isCompleted").value(true));
//    }
//
//    @Test
//    public void testAddTodo() throws Exception {
//        // 准备测试数据
//        Todo newTodo = new Todo();
//        newTodo.setValue("新待办事项");
//        newTodo.setIsCompleted(false);
//
//        Todo savedTodo = new Todo();
//        savedTodo.setId(1L);
//        savedTodo.setValue("新待办事项");
//        savedTodo.setIsCompleted(false);
//
//        // 模拟服务层行为
//        when(todoService.addTodo(any(Todo.class))).thenReturn(savedTodo);
//
//        // 执行测试并验证结果
//        mockMvc.perform(post("/api/add-todo")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(newTodo)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.value").value("新待办事项"))
//                .andExpect(jsonPath("$.isCompleted").value(false));
//
//        // 测试无效输入
//        Todo invalidTodo = new Todo();
//        invalidTodo.setValue(""); // 空值
//
//        mockMvc.perform(post("/api/add-todo")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(invalidTodo)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateTodoStatus() throws Exception {
//        // 准备测试数据
//        Todo updatedTodo = new Todo();
//        updatedTodo.setId(1L);
//        updatedTodo.setValue("待办事项");
//        updatedTodo.setIsCompleted(true);
//
//        // 模拟服务层行为
//        when(todoService.updateTodoStatus(1L)).thenReturn(updatedTodo);
//        when(todoService.updateTodoStatus(999L)).thenThrow(new RuntimeException("Todo item not found"));
//
//        // 测试成功更新
//        mockMvc.perform(post("/api/update-todo/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.isCompleted").value(true));
//
//        // 测试更新不存在的待办事项
//        mockMvc.perform(post("/api/update-todo/999"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteTodo() throws Exception {
//        // 模拟服务层行为
//        when(todoService.deleteTodo(1L)).thenReturn(true);
//        when(todoService.deleteTodo(999L)).thenThrow(new RuntimeException("Todo item not found"));
//
//        // 测试成功删除
//        mockMvc.perform(post("/api/del-todo/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true));
//
//        // 测试删除不存在的待办事项
//        mockMvc.perform(post("/api/del-todo/999"))
//                .andExpect(status().isNotFound());
//    }
//}