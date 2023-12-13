package com.isaachome.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
public class HomeController {

    @GetMapping
    public  String getUser(){
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(r-> System.out.println("Role : " + r.getAuthority().toString()));
      return "Hello World";
    }
}
