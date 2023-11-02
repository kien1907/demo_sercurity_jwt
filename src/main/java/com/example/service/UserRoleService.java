package com.example.service;

import com.example.entity.Roles;
import com.example.entity.UserRole;

import java.util.List;

public interface UserRoleService {
   void saveOrUpdate (UserRole userRole);
   List<Roles> findRoleByUserId(Integer id);
}
