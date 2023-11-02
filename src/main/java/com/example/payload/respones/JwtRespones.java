package com.example.payload.respones;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class JwtRespones {
    private String token;
    private String type = "Bearer";
    private Integer userId;
    private String userName;
    private List<String> listRoles;

    public JwtRespones(String token, Integer userId, String userName, List<String> listRoles) {
        this.token = token;
        this.userId = userId;
        this.userName = userName;
        this.listRoles = listRoles;
    }
}
