package com.isaac.learn.resourceserver.config;

import com.isaac.learn.resourceserver.security.JwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {



    @Value("${keySetURI}")
    private String keySetUri;
    private final JwtAuthenticationConverter converter;

    public ProjectConfig(JwtAuthenticationConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer((c)->c.jwt(
                j->j.jwkSetUri(keySetUri)
                        .jwtAuthenticationConverter(converter)
        ));
        http.authorizeHttpRequests((r)->r.anyRequest().authenticated());
        return http.build();
    }
}
