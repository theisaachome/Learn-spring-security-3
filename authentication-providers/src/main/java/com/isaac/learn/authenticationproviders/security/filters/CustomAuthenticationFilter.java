package com.isaac.learn.authenticationproviders.security.filters;

import com.isaac.learn.authenticationproviders.security.authentication.CustomAuthentication;
import com.isaac.learn.authenticationproviders.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        String key = request.getHeader("key");
        var ca = new CustomAuthentication(false,key);

        var a = authenticationManager.authenticate(ca);
        if(a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response);
        }
    }
}
