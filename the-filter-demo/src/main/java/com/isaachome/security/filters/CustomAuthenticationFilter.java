package com.isaachome.security.filters;

import com.isaachome.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        var authentication = new CustomAuthentication(authorization,null);
        try {
           var result = authenticationManager.authenticate(authentication);
            if(result.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(result);
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }catch (AuthenticationException e){
             httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
