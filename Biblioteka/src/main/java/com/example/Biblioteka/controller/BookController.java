package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.BookDto;
import com.example.Biblioteka.exception.AuthorNotFoundException;
import com.example.Biblioteka.exception.BookNotFoundException;
import com.example.Biblioteka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        try {
            BookDto savedBook = bookService.addBook(bookDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (AuthorNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        try {
            BookDto book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/fetch-and-save")
    public String fetchAndSaveBooks(@RequestParam List<String> authorSlugs) {

        if (authorSlugs.size() != 5) {
            return "Zła liczba książek!";
        }

        bookService.fetchAndSaveBooks(authorSlugs);
        return "Zapisano książki";
    }

}
