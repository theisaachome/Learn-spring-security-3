package com.myomyo.controller;
import com.myomyo.model.Book;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    //localhost:8080/api/v1/books
    @GetMapping
    public Book getBook(){
        return new Book(1,"Java in Action","Josh German");
    }

//    emailB
   // Path/variable
    //PathVariable
    //localhost:8080/api/v1/books
    @GetMapping("{id}")
    public Book getBookById(@PathVariable("id") long bookId){
        return new Book(bookId,"Java in Action","Josh German");
    }

    //localhost:8080/api/v1/books/1900/kyaw/JavabasicInAction
    @GetMapping("{id}/{title}/{author}")
    public Book getBookByFields(@PathVariable("id")long bookId,
                                @PathVariable("title")String title,
                                @PathVariable("author")String author){
        return new Book(bookId,title,author);
    }

    //
    //localhost:8080/api/v1/books/query?bookId=1&title=JavaInAction&author=MyoMyo
    @GetMapping("/query")
    public Book queryBookByFields(@RequestParam long bookId,
                                  @RequestParam String title,
                                  @RequestParam String author){
        return new Book(bookId,title,author);
    }
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return new Book(book.getId(),book.getTitle(),book.getAuthor());
    }




}
