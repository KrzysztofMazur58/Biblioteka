package com.example.Biblioteka.service;


import com.example.Biblioteka.model.Author;
import com.example.Biblioteka.dtos.AuthorDto;
import com.example.Biblioteka.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorDto addAuthor(AuthorDto authorDto)
    {
        Author author = new Author();
        author.setName(authorDto.getName());
        Author savedAuthor = authorRepository.save(author);

        return toDto(savedAuthor);
    }

    public AuthorDto getAuthorById(Long id)
    {
        Author author = authorRepository.findById(id)
                .orElseThrow();

        return toDto(author);
    }

    public List<AuthorDto> getAllAuthors()
    {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AuthorDto toDto(Author author)
    {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }
}
