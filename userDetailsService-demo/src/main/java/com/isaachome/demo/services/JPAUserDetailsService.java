package com.isaachome.demo.services;

import com.isaachome.demo.config.SecurityUser;
import com.isaachome.demo.entity.User;
import com.isaachome.demo.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {


    @Autowired
    private  UserRepos userRepos;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepos.findByEmail(username);
             User u=user.orElseThrow(()-> new UsernameNotFoundException("Not User Found."));
        return new SecurityUser(u);
    }
}
