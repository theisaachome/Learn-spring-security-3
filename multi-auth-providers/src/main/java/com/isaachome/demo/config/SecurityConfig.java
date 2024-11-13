package com.isaachome.demo.config;

import com.isaachome.demo.config.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Value("${the.secret}")
    private  String key;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{

    return  http.httpBasic( Customizer.withDefaults())
            .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(auth->{
                auth.anyRequest().authenticated();
            })
            .build();

    }
}
