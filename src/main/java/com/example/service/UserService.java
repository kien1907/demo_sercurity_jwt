package com.example.service;

import com.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService  {
    Users findById(Integer id);
    Users findByUsername(String username);
    boolean existsByUsername(String username);
    void saveOrUpdate(Users users);
}
