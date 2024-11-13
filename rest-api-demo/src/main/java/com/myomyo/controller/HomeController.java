package com.myomyo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class HomeController {

//    @GetMapping
//    public String home(){
//        return  "Hello";
//    }

    @GetMapping
    public List<Post> home(){

        return  List.of(new Post("1","1","Myo Myo Is rocking","quo et expedita modi cum officia vel magni"),
                new Post("1","1","Myo Myo Is rocking","quo et expedita modi cum officia vel magni"),
                new Post("1","1","Myo Myo Is rocking","quo et expedita modi cum officia vel magni"),
                new Post("1","1","Myo Myo Is rocking","quo et expedita modi cum officia vel magni"),
                new Post("1","1","Myo Myo Is rocking","quo et expedita modi cum officia vel magni"));
    }


}

class Post{
    private String userId;
    private String id;
    private  String title;
    private String body;

    public Post(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}