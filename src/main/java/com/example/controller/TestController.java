package com.example.controller;

import com.example.entity.Roles;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/all")
    public String all(){
        return "page all";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MODERATOR') ")
    public String user(){
        return "user";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "page Admin";
    }
    @GetMapping("/mod")
    @ResponseBody
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderator(){
        return "page Mode";
    }


    @GetMapping("/user-role")
    public List<Roles> test(){
        return userRoleService.findRoleByUserId(1);
    }

}
