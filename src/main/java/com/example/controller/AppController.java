package com.example.controller;
import com.example.entity.ERole;
import com.example.entity.Roles;
import com.example.entity.UserRole;
import com.example.entity.Users;
import com.example.jwt.JwtTokenProvider;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.SingupRequest;
import com.example.payload.respones.JwtRespones;
import com.example.payload.respones.MessagerRespones;
import com.example.sercurity.CusTomUserDetail;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AppController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PasswordEncoder encoder;



    @PostMapping("/singup")
    private ResponseEntity<?> registerUser(@RequestBody SingupRequest singupRequest){
            if(userService.existsByUsername(singupRequest.getUsername())){
                return ResponseEntity.badRequest().body(new MessagerRespones("username is already"));
            }
        Users user = Users.builder()
                .username(singupRequest.getUsername())
                .passcode(encoder.encode(singupRequest.getPassword()))
                .build();
        userService.saveOrUpdate(user);
        Set<String> strRole = singupRequest.getListRoles();
        Set<Roles> listRole = new HashSet<>();
        if (strRole == null){
            Roles userRole = roleService.findRoleByName(ERole.ROLE_USER);
            listRole.add(userRole);
        }else {
            strRole.forEach(role->{
                switch (role){
                    case "admin":
                        Roles adminRole = roleService.findRoleByName(ERole.ROLE_ADMIN);
                        listRole.add(adminRole);
                        break;
                    case "user":
                        Roles userRole = roleService.findRoleByName(ERole.ROLE_USER);
                        listRole.add(userRole);
                        break;
                    case "moderator":
                        Roles moderatorRole = roleService.findRoleByName(ERole.ROLE_MODERATOR);
                        listRole.add(moderatorRole);
                        break;
                }
            });
        }
        Users findUser = userService.findByUsername(singupRequest.getUsername());
        listRole.forEach(roles -> {
            UserRole userRole = new UserRole(null,findUser,roles);
            userRoleService.saveOrUpdate(userRole);
        });
        return ResponseEntity.ok(new MessagerRespones("Thêm Thành Công"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),loginRequest.getPassword()
                        );
        System.out.println(authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CusTomUserDetail cusTomUserDetail = (CusTomUserDetail) authentication.getPrincipal();
        String jwt = jwtTokenProvider.generateToken(cusTomUserDetail);
        List<String> listRoles = cusTomUserDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtRespones(jwt,cusTomUserDetail.getId(),
                cusTomUserDetail.getUsername(),
                listRoles
                ));
    }


    @GetMapping("/find")
    public Users find(@RequestParam("ma") Integer ma){
        System.out.println(ma);
        return userService.findById(ma);
    }
}
