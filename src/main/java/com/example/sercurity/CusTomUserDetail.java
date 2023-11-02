package com.example.sercurity;

import com.example.entity.Users;
import com.example.reposistory.UserRoleReposistory;
import com.example.service.UserRoleService;
import com.example.service.impl.UserRoleServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CusTomUserDetail implements UserDetails {
    private Integer id;
    private String username;
    private  String passcode;
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // map tu user sang customuserdetail
    public CusTomUserDetail mapUserToUserDetail(Users users){

        System.out.println("customDetail"+users.getUsername());
        List<GrantedAuthority> listAuthorities = users.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getNameRole().name()))
                .collect(Collectors.toList());
        return new CusTomUserDetail(
                users.getId(),users.getUsername(),users.getPasscode(),listAuthorities);
    }

    @Override
    public String getPassword() {
        return this.passcode;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
