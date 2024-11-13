package com.isaachome.demo.config.security.provider;

import com.isaachome.demo.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${secret.key}")
    private  String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication =(CustomAuthentication) authentication;
        String headerKey = customAuthentication.getKey();
        if(this.key.equals(headerKey)){
            return  new CustomAuthentication(true,null);
        }
       throw new BadCredentialsException("Sorry Unauthenticated");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
