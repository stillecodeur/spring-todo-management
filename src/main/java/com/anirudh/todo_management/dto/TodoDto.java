package com.anirudh.todo_management.dto;

public record TodoDto(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
