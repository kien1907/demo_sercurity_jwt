package com.example.service.impl;

import com.example.entity.ERole;
import com.example.entity.Roles;
import com.example.reposistory.RolesReposistory;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolesReposistory reposistory;

    @Override
    public Roles findRoleByName(ERole name) {
        return reposistory.findByNameRole(name).get();
    }
}
