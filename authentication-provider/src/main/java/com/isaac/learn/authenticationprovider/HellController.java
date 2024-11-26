package com.isaac.learn.authenticationprovider;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HellController {

    @GetMapping("/hello")
    public String hello(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();
        return  "Hello " + authentication.getName() + "!";
    }
}
