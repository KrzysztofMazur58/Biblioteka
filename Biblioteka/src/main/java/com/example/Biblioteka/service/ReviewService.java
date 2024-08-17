package com.example.Biblioteka.service;

import com.example.Biblioteka.dtos.ReviewDto;
import com.example.Biblioteka.model.Book;
import com.example.Biblioteka.model.Review;
import com.example.Biblioteka.model.Reviewer;
import com.example.Biblioteka.repository.BookRepository;
import com.example.Biblioteka.repository.ReviewRepository;
import com.example.Biblioteka.repository.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewerRepository reviewerRepository;
    private final BookRepository bookRepository;

    public Long addReview(ReviewDto reviewDto)
    {
        Review review = new Review();

        review.setRating(reviewDto.getRating());

        Reviewer reviewer = reviewerRepository.findById(reviewDto.getReviewerId())
                .orElseThrow();

        Book book = bookRepository.findById(reviewDto.getBookId())
                .orElseThrow();

        review.setReviewer(reviewer);
        review.setBook(book);

        return reviewRepository.save(review).getId();
    }

    public ReviewDto toDto(Review review)
    {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReviewerId(review.getReviewer().getId());
        reviewDto.setRating(review.getRating());
        reviewDto.setBookId(review.getBook().getId());

        return reviewDto;
    }
}
