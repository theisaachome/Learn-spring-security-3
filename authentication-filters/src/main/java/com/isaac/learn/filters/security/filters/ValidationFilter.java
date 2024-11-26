package com.isaac.learn.filters.security.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        var requestId = request.getHeader("request-id");

        if (requestId == null || requestId.isEmpty()) response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        filterChain.doFilter(request, response);
    }
}
