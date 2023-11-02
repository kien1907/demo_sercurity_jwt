package com.example.service;

import com.example.entity.ERole;
import com.example.entity.Roles;

public interface RoleService {

    Roles findRoleByName(ERole name);
}
