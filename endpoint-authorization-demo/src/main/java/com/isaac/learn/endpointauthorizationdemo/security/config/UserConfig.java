package com.isaac.learn.endpointauthorizationdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        var one = User.withUsername("one")
                .password(passwordEncoder().encode("pw"))
                .authorities("READ")
//                .roles("ADMIN")
                .build();
        var two = User.withUsername("two")
                .password(passwordEncoder().encode("pw"))
                .authorities("WRITE")
//                .roles("MANAGER")
                .build();
        uds.createUser(one);
        uds.createUser(two);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
