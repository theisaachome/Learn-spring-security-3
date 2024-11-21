package com.isaac.learn.config;

import com.isaac.learn.security.AuthenticationLoggingFilter;
import com.isaac.learn.security.RequestValidationFilter;
import com.isaac.learn.security.StaticKeyAuthenticationFilter;
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
//        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
//                .authorizeRequests((c)-> c.anyRequest().permitAll());

        http.addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeRequests((c)->c.anyRequest().permitAll());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
