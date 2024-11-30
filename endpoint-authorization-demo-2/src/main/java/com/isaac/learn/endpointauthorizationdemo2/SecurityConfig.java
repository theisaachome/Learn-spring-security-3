package com.isaac.learn.endpointauthorizationdemo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        var read = User.withDefaultPasswordEncoder()
                .username("read")
                .password("pw")
                .authorities("read")
                .build();

        var write = User.withDefaultPasswordEncoder()
                .username("write")
                .password("pw")
                .authorities("write")
                .build();

        return new InMemoryUserDetailsManager(read,write);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return  new BCryptPasswordEncoder();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests((auth)-> auth
                .requestMatchers("/api/test1").hasAuthority("read")
                .requestMatchers("/api/test2").hasAuthority("write"));
        http.authorizeHttpRequests((auth)->auth.anyRequest().authenticated());

        return http.build();
    }
}
