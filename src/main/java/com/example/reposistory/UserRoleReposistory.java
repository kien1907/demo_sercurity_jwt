package com.example.reposistory;

import com.example.entity.ERole;
import com.example.entity.Roles;
import com.example.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserRoleReposistory extends JpaRepository<UserRole,Integer> {
    @Query("select ur.role From UserRole ur where ur.user.id = :id")
    List<Roles> findRoelByUserId(@Param("id") Integer id);
}
