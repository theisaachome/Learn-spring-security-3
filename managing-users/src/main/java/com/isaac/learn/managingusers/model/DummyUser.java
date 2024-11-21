package com.isaac.learn.managingusers.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DummyUser implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( () -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return "Bill";
    }

    @Override
    public String getUsername() {
        return "12345";
    }
}
