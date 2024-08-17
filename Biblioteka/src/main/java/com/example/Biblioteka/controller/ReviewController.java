package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.ReviewDto;
import com.example.Biblioteka.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Long addReview(@RequestBody ReviewDto reviewDto)
    {
        return reviewService.addReview(reviewDto);
    }
}
