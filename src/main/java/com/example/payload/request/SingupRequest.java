package com.example.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter@Setter
public class SingupRequest {
    private String username;
    private String password;
    private Set<String> listRoles;
}
