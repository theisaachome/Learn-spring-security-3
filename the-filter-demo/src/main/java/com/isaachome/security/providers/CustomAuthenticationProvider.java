package com.isaachome.security.providers;

import com.isaachome.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {
    @Value("{key}")
    private String requestKey;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String key = authentication.getName();
        if(requestKey.equals(key)){
            return new CustomAuthentication(null,null,null);
        }else {
            throw new BadCredentialsException("Username or password wrong.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomAuthentication.class.equals(aClass);
    }
}
