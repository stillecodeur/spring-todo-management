package com.anirudh.todo_management.controller;

import com.anirudh.todo_management.dto.TodoDto;
import com.anirudh.todo_management.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto dto = todoService.createTodo(todoDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        TodoDto dto = todoService.getTodo(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos() {
        List<TodoDto> dtos = todoService.getAllTodos();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto) {
        TodoDto dto = todoService.updateTodo(todoDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> completeTask(@PathVariable Long id) {
        TodoDto todoDto = todoService.completeTask(id);
        return ResponseEntity.ok(todoDto);
    }
}
