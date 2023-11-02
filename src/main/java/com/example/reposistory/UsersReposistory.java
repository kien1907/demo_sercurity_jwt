package com.example.reposistory;

import com.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ref.SoftReference;

public interface UsersReposistory extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
