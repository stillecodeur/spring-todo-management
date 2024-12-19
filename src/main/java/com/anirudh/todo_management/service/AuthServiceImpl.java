package com.anirudh.todo_management.service;

import com.anirudh.todo_management.dto.LoginDto;
import com.anirudh.todo_management.dto.RegisterDto;
import com.anirudh.todo_management.entity.Role;
import com.anirudh.todo_management.entity.User;
import com.anirudh.todo_management.exception.TodoApiException;
import com.anirudh.todo_management.repository.RoleRepository;
import com.anirudh.todo_management.repository.UserRepository;
import com.anirudh.todo_management.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    @Override
    public String register(RegisterDto dto) {

        if(userRepository.existsByUsername(dto.getUsername())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"Username already exist");
        }

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"Email already exist");
        }

        User user=new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.getToken(authentication);
    }
}
