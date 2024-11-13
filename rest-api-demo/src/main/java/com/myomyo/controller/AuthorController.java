package com.myomyo.controller;
import com.myomyo.payload.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    // Create New Author
    // localhost:8080/api/v1/authors
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO dto){
        var newAuthor = new AuthorDTO(1, dto.getName(), dto.getMail(), dto.getPhone(), dto.getBiography());
        return  new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }

}
