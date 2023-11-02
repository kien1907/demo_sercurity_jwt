package com.example.payload.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
