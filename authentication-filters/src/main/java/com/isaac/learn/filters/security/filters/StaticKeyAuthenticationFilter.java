package com.isaac.learn.filters.security.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class StaticKeyAuthenticationFilter implements Filter {

    @Value("${authentication.key}")
    private String authenticationKey;
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var authentication = request.getHeader("Authorization");
        if (authentication != null && authentication.equalsIgnoreCase(authenticationKey)) {
            filterChain.doFilter(request, response);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
