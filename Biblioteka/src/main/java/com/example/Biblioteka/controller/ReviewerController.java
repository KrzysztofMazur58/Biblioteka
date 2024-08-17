package com.example.Biblioteka.controller;

import com.example.Biblioteka.dtos.ReviewerDto;
import com.example.Biblioteka.exception.ReviewerNotFoundException;
import com.example.Biblioteka.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ReviewerDto> addReviewer(@RequestBody ReviewerDto reviewerDto) {
        ReviewerDto addedReviewer = reviewerService.addReviewer(reviewerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReviewer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewerDto> getReviewerById(@PathVariable Long id) {
        try {
            ReviewerDto reviewer = reviewerService.getReviewerById(id);
            return ResponseEntity.ok(reviewer);
        } catch (ReviewerNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReviewerDto>> getAllReviewers() {
        List<ReviewerDto> reviewers = reviewerService.getAllReviewers();
        return ResponseEntity.ok(reviewers);
    }
}
