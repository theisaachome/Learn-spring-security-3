package com.isaac.learn.endpointauthorizationdemo.security.config;

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
import org.springframework.security.web.context.SecurityContextHolderFilter;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.httpBasic(Customizer.withDefaults());
//        http.authorizeHttpRequests((auth)->auth.anyRequest().authenticated());

//        http.authorizeHttpRequests((auth)->
//                auth.requestMatchers("/demo/admin")
//                        .hasRole("ADMIN")
//                        .hasAnyRole("ADMIN", "USER")
//                        );
//        http.authorizeHttpRequests(
//                (auth)->
//                 auth.requestMatchers("/demo/manager")
//                .hasAuthority("read")
//                         .hasAnyAuthority("read","write")
//                .anyRequest().permitAll());

//        http.authorizeHttpRequests((auth)->auth.anyRequest().denyAll());
//            http.authorizeHttpRequests(
//                    (authorizeRequests) ->
//                            authorizeRequests.anyRequest()
//                                    .permitAll());

//        http.authorizeHttpRequests((auth)->auth.requestMatchers("/demo/**").authenticated());

//        http.authorizeHttpRequests(
//                (rq)->rq.anyRequest()
//                        .hasAuthority("WRITE"));
//        http.authorizeHttpRequests((authorizeRequests) -> {
//            authorizeRequests.anyRequest()
//                    .hasAnyAuthority("WRITE","READ");
//        });

        http.authorizeHttpRequests((authorizeRequests) -> {
            authorizeRequests.anyRequest().hasAnyRole("WRITE","READ");
        });
        return  http.build();
    }


}
