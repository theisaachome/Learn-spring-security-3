package com.isaac.learn.multipleauthenticationproviders.security.provider;

import com.isaac.learn.multipleauthenticationproviders.security.authentication.ApiKeyAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private final String key;
    public ApiKeyAuthenticationProvider(String key) {
        this.key = key;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var auth =(ApiKeyAuthentication) authentication;
        if(key.equals(auth.getKey())) {
            auth.setAuthenticated(true);
            return auth;
        }
        throw  new BadCredentialsException("Authentication Failed :(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
