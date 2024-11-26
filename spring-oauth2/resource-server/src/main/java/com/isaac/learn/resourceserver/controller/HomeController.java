package com.isaac.learn.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class HomeController {

    @GetMapping
    public String demo(){
        return "Demo";
    }
}
