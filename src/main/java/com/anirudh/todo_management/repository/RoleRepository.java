package com.anirudh.todo_management.repository;

import com.anirudh.todo_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {

    Role findByName(String role);
}
