package com.anirudh.todo_management.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl{


    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("johnpass"));
        System.out.println(passwordEncoder.encode("adminpass"));
        System.out.println("e3b3b5e1-acbf-46fb-8579-e18f9f9836b0".length());
    }
}
