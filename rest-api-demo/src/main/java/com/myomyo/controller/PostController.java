package com.myomyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    @RequestMapping("/home.html")
    @ResponseBody
    public  String hello(ModelAndView modelAndView){
         return  "home";
    }
}
