package com.anirudh.todo_management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TodoApiException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;



}
