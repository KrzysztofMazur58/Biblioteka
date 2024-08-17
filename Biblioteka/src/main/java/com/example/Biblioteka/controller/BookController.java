package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.BookDto;
import com.example.Biblioteka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController (BookService bookService)
    {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookDto bookDto)
    {
        return bookService.addBook(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto getBookIdById(@PathVariable Long id)
    {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<BookDto> getAllBooks()
    {
        return bookService.getAllBooks();
    }
}
