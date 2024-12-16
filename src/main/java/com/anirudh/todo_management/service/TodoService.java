package com.anirudh.todo_management.service;

import com.anirudh.todo_management.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto);

    void deleteTodo(Long id);

    TodoDto completeTask(Long id);

    TodoDto inCompleteTask(Long id);
}
