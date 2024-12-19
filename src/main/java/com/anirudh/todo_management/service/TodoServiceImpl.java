package com.anirudh.todo_management.service;


import com.anirudh.todo_management.dto.TodoDto;
import com.anirudh.todo_management.entity.Todo;
import com.anirudh.todo_management.exception.TodoApiException;
import com.anirudh.todo_management.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {


    private TodoRepository repository;
    private ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        return modelMapper.map(repository.save(todo), TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoApiException(HttpStatus.NOT_FOUND,"Todo doesn't exist"));
        return modelMapper.map(repository.save(todo), TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = repository.findAll();
        return todoList.stream().map((todo -> modelMapper.map(todo, TodoDto.class))).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto) {
        Todo todo = repository.findById(todoDto.getId()).orElseThrow(() -> new TodoApiException(HttpStatus.NOT_FOUND,"Todo doesn't exist"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoApiException(HttpStatus.NOT_FOUND,"Todo doesn't exist"));
        repository.deleteById(id);
    }

    @Override
    public TodoDto completeTask(Long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoApiException(HttpStatus.NOT_FOUND,"Todo doesn't exist"));
        todo.setCompleted(Boolean.TRUE);
        Todo todoUpdated = repository.save(todo);
        return modelMapper.map(todoUpdated, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTask(Long id) {
        Todo todo = repository.findById(id).orElseThrow(() -> new TodoApiException(HttpStatus.NOT_FOUND,"Todo doesn't exist"));
        todo.setCompleted(Boolean.FALSE);
        Todo todoUpdated = repository.save(todo);
        return modelMapper.map(todoUpdated, TodoDto.class);
    }
}
