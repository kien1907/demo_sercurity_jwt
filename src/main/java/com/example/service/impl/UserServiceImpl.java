package com.example.service.impl;

import com.example.entity.Users;
import com.example.reposistory.UsersReposistory;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersReposistory usersReposistory;

    @Override
    public Users findById(Integer id) {
        return usersReposistory.findById(id).get();
    }

    @Override
    public Users findByUsername(String username) {
        return usersReposistory.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usersReposistory.existsByUsername(username);
    }

    @Override
    public void saveOrUpdate(Users users) {
        usersReposistory.save(users);
    }
}
