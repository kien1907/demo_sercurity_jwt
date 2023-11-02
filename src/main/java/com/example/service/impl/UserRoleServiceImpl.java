package com.example.service.impl;

import com.example.entity.Roles;
import com.example.entity.UserRole;
import com.example.reposistory.UserRoleReposistory;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleReposistory userRoleReposistory;

    @Override
    public void saveOrUpdate(UserRole userRole) {
        userRoleReposistory.save(userRole);
    }

    @Override
    public List<Roles> findRoleByUserId(Integer id) {
        return userRoleReposistory.findRoelByUserId(7);
    }

}
