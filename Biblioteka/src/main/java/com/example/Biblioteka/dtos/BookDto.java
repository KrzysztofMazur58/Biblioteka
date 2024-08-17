package com.example.Biblioteka.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;
    private Long authorId;
}
