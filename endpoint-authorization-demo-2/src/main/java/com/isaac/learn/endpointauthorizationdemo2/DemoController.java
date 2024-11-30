package com.isaac.learn.endpointauthorizationdemo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/test1")
    public String test1(){
        return  "Read Resource !";
    }

    @GetMapping("/test2")
    public String test2(){
        return "Write Resource !";
    }
}
