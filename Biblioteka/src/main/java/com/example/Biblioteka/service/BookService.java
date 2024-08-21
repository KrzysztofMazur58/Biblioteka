package com.example.Biblioteka.service;

import com.example.Biblioteka.exception.AuthorNotFoundException;
import com.example.Biblioteka.exception.BookNotFoundException;
import com.example.Biblioteka.model.Author;
import com.example.Biblioteka.model.Book;
import com.example.Biblioteka.dtos.BookDto;
import com.example.Biblioteka.model.BookFromApi;
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

    public BookDto addBook(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + bookDto.getAuthorId()));

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        return toDto(savedBook);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return toDto(book);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }

    private BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setAuthorId(book.getAuthor().getId());
        return bookDto;
    }

    private Book toEntity(BookFromApi bookFromApi)
    {
        Book book = new Book();
        book.setTitle(bookFromApi.getTitle());
        book.setIsbn("");
        book.setPublicationYear(0);
        return book;
    }

    public void saveBooks(List<BookFromApi> books) {
        List<Book> bookEntities = books.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        bookRepository.saveAll(bookEntities);
    }

    public void fetchAndSaveBooks(List<String> authorSlugs)
    {
        for(String authorSlug : authorSlugs)
        {
            List<BookFromApi> books = new WolneLekturyClient().getBooksByAuthor(authorSlug);
            saveBooks(books);
        }
    }

}
