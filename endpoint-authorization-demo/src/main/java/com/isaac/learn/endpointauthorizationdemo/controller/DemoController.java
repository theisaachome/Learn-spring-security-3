package com.isaac.learn.endpointauthorizationdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String home(){
        return "Hello World! from Home";
    }

    @GetMapping("/admin")
    public String demo(){
        return "Hello World from demo";
    }

    @GetMapping("/manager")
    public String getManger(){
        return "Manger Resource";
    }
}
