package com.isaac.learn.multipleauthenticationproviders.security.filters;

import com.isaac.learn.multipleauthenticationproviders.security.authentication.ApiKeyAuthentication;
import com.isaac.learn.multipleauthenticationproviders.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;

@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {


    private final String key;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var headerKey = request.getHeader("x-api-key");

        if (headerKey == null|| "null".equals(headerKey)) {
            filterChain.doFilter(request, response);
        }

        var manager = new CustomAuthenticationManager(key);

        var auth = new ApiKeyAuthentication(headerKey);

        var a = manager.authenticate(auth);
        if (a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
