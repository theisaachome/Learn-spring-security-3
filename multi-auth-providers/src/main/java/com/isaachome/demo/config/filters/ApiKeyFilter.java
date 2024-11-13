package com.isaachome.demo.config.filters;

import com.isaachome.demo.config.authentications.ApiKeyAuthentication;
import com.isaachome.demo.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {
    private  String key;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws ServletException, IOException {
      // Authentication Manager
        CustomAuthenticationManager authenticationManager = new CustomAuthenticationManager(key);
        var requestKey = req.getHeader("x-api-key");
      if(null== requestKey || "null".equals(requestKey)){
          filter.doFilter(req,res);
      }
      var auth = new ApiKeyAuthentication(key);
      try {
        var a = authenticationManager.authenticate(auth);
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filter.doFilter(req,res);
        }else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
      }catch (AuthenticationException e){
          res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    }
}
