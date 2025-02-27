package com.merak.todolist.domain.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "todo", indexes = {
    @jakarta.persistence.Index(name = "idx_is_completed", columnList = "is_completed")
})
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String value;
    
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;
}