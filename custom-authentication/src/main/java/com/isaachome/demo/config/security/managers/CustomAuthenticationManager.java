package com.isaachome.demo.config.security.managers;

import com.isaachome.demo.config.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private  final CustomAuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authenticationProvider.supports(authentication.getClass())){
          return authenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh No!");
    }
}
