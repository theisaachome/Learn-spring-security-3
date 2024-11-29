package com.isaac.learn.multipleauthenticationproviders.security.managers;

import com.isaac.learn.multipleauthenticationproviders.security.provider.ApiKeyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final  String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new ApiKeyAuthenticationProvider(key);
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        return authentication;
    }
}
