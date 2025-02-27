package com.merak.todolist.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TodoDTO {
    private Long id;
    
    @NotBlank(message = "Todo item description cannot be empty")
    private String value;


    private Boolean isCompleted;
}