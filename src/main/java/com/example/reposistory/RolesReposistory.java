package com.example.reposistory;

import com.example.entity.ERole;
import com.example.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesReposistory extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByNameRole(ERole name);
}
