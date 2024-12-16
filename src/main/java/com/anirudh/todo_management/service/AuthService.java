package com.anirudh.todo_management.service;

import com.anirudh.todo_management.dto.LoginDto;
import com.anirudh.todo_management.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto dto);
    String login(LoginDto loginDto);
}
