package com.isaac.learn.multipleauthenticationproviders.security.config;

import com.isaac.learn.multipleauthenticationproviders.security.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Value("${the.secret}")
    private String key;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class);
        http.authorizeHttpRequests((auth)->auth.anyRequest().authenticated());
        return  http.build();
    }
}
