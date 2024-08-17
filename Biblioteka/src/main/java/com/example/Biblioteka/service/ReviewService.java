package com.example.Biblioteka.service;

import com.example.Biblioteka.dtos.ReviewDto;
import com.example.Biblioteka.exception.BookNotFoundException;
import com.example.Biblioteka.exception.ReviewerNotFoundException;
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

    public Long addReview(ReviewDto reviewDto) {
        Reviewer reviewer = reviewerRepository.findById(reviewDto.getReviewerId())
                .orElseThrow(() -> new ReviewerNotFoundException("Reviewer not found with id: " + reviewDto.getReviewerId()));

        Book book = bookRepository.findById(reviewDto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + reviewDto.getBookId()));

        Review review = new Review();
        review.setRating(reviewDto.getRating());
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
