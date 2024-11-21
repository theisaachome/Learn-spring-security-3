package com.isaac.learn.security;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class AuthenticationLoggingFilter implements Filter {

    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        String requestID = request.getHeader("X-Request-ID");
        logger.info("Successfully authenticated request with ID: " + requestID);

        filterChain.doFilter(request, servletResponse);
    }
}
