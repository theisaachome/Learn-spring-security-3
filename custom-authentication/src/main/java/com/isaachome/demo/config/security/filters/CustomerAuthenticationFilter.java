package com.isaachome.demo.config.security.filters;

import com.isaachome.demo.config.security.authentication.CustomAuthentication;
import com.isaachome.demo.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomerAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = request.getHeader("key");
        // 1. create an authentication object which is not yet authenticated
        CustomAuthentication auth = new CustomAuthentication(false,key);
        // 2. delegate the authentication object to the manager
        var a= customAuthenticationManager.authenticate(auth);
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }
    }
}
