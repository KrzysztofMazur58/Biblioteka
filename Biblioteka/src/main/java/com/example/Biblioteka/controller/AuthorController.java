package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.AuthorDto;
import com.example.Biblioteka.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto)
    {
        AuthorDto author = authorService.addAuthor(authorDto);
        return ResponseEntity.badRequest().body(author);
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id)
    {
        return authorService.getAuthorById(id);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }
}
