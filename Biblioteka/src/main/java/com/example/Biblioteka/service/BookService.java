package com.example.Biblioteka.service;

import com.example.Biblioteka.model.Author;
import com.example.Biblioteka.model.Book;
import com.example.Biblioteka.dtos.BookDto;
import com.example.Biblioteka.repository.AuthorRepository;
import com.example.Biblioteka.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookDto addBook(BookDto bookDto)
    {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPublicationYear(bookDto.getPublicationYear());

        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow();
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        return toDto(savedBook);
    }

    public BookDto getBookById(Long id)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow();
        return toDto(book);
    }

    public List<BookDto> getAllBooks()
    {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
    public BookDto toDto(Book book)
    {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setAuthorId(book.getAuthor().getId());
        return bookDto;
    }

}
