package com.isaac.learn.authenticationproviders.security.managers;

import com.isaac.learn.authenticationproviders.security.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomAuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authenticationProvider.supports(authentication.getClass())){
            return authenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Bad credentials");
    }
}
