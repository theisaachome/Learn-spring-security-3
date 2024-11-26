package com.isaac.learn.filters.config;

import com.isaac.learn.filters.security.filters.StaticKeyAuthenticationFilter;
import com.isaac.learn.filters.security.filters.ValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final StaticKeyAuthenticationFilter filter;

    public SecurityConfig(StaticKeyAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults())
                .addFilterBefore(new ValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests((auth)->auth.anyRequest().permitAll());
        return  http.build();
    }
}
