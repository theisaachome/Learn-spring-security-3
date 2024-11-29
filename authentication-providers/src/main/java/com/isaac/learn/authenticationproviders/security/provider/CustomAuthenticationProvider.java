package com.isaac.learn.authenticationproviders.security.provider;

import com.isaac.learn.authenticationproviders.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.key}")
    private String key ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       CustomAuthentication ca = (CustomAuthentication) authentication;
       String headerKey  = ca.key();
       if(headerKey.equals(key)){
           return new CustomAuthentication(true,null);
       }
       throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
