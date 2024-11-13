package com.isaachome.demo.config;

import com.isaachome.demo.config.security.filters.CustomerAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomerAuthenticationFilter filter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        return  http.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((auth)->{
                    auth.anyRequest().authenticated();
                }).build();
    }
}
