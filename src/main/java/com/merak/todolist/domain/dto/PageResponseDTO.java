package com.merak.todolist.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageResponseDTO<T> {
    private List<T> data;
    private long total;
    private int currentPage;
    private int pageSize;

    public static <T> PageResponseDTO<T> of(List<T> data, long total, int currentPage, int pageSize) {
        PageResponseDTO<T> response = new PageResponseDTO<>();
        response.setData(data);
        response.setTotal(total);
        response.setCurrentPage(currentPage);
        response.setPageSize(pageSize);
        return response;
    }
}