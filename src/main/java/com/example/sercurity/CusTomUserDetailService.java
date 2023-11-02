package com.example.sercurity;

import com.example.entity.Users;
import com.example.reposistory.UsersReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CusTomUserDetailService implements UserDetailsService {

    @Autowired
    UsersReposistory usersReposistory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = usersReposistory.findByUsername(username); khi set subjec là user name
        Users users = usersReposistory.findByUsername(username);
        try {
            CusTomUserDetail cusTomUserDetail = new CusTomUserDetail();
            return cusTomUserDetail.mapUserToUserDetail(users);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
