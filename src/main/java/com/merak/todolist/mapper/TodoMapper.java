package com.merak.todolist.mapper;

import com.merak.todolist.domain.entity.Todo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TodoMapper {
    List<Todo> findAll();
    
    List<Todo> findAllWithPagination(@Param("offset") int offset, 
                                   @Param("pageSize") int pageSize, 
                                   @Param("isCompleted") Boolean isCompleted);
    
    long getTotalCount(@Param("isCompleted") Boolean isCompleted);
    
    int insert(Todo todo);
    
    int batchInsert(List<Todo> todos);
    
    int updateStatus(Todo todo);
    
    int batchUpdateStatus(List<Todo> todos);
    
    int deleteById(Long id);
    
    Todo findById(Long id);

    int resetAutoIncrement();
}