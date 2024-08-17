package com.example.Biblioteka.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDto {

    private Long id;
    private int rating;
    private Long bookId;
    private Long reviewerId;
}
