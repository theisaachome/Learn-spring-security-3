package com.isaac.learn.filters.security.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthenticationLoggingFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var requestID = request.getHeader("request-id");
        if (requestID != null) {
            logger.info("Authenticated Successfully request with ID : " + requestID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
