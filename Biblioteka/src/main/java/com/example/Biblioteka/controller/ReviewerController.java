package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.ReviewerDto;
import com.example.Biblioteka.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewers")
public class ReviewerController {

    private final ReviewerService reviewerService;

    @Autowired
    public ReviewerController(ReviewerService reviewerService)
    {
        this.reviewerService = reviewerService;
    }

    @PostMapping
    public ReviewerDto addReviewer(@RequestBody ReviewerDto reviewerDto)
    {
        return reviewerService.addReviewer(reviewerDto);
    }

    @GetMapping("/{id}")
    public ReviewerDto getReviewerById(@PathVariable Long id)
    {
       return reviewerService.getReviewerById(id);
    }

    @GetMapping
    public List<ReviewerDto> getAllReviewers()
    {
        return reviewerService.getAllReviewers();
    }
}
