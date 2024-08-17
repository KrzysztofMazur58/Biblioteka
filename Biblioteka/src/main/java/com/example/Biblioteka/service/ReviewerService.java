package com.example.Biblioteka.service;

import com.example.Biblioteka.model.Reviewer;
import com.example.Biblioteka.dtos.ReviewerDto;
import com.example.Biblioteka.repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;

    @Autowired
    public ReviewerService(ReviewerRepository reviewerRepository)
    {
        this.reviewerRepository = reviewerRepository;
    }

    public ReviewerDto addReviewer(ReviewerDto reviewerDto)
    {
        Reviewer reviewer = new Reviewer();
        reviewer.setName(reviewerDto.getName());
        Reviewer savedReviewer = reviewerRepository.save(reviewer);
        return toDto(savedReviewer);
    }

    public ReviewerDto getReviewerById(Long id)
    {
        Reviewer reviewer = reviewerRepository.findById(id)
                .orElseThrow();
        return toDto(reviewer);
    }

    public List<ReviewerDto> getAllReviewers()
    {
        List<Reviewer> reviewers = reviewerRepository.findAll();
        return reviewers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ReviewerDto toDto(Reviewer reviewer)
    {
        ReviewerDto reviewerDto = new ReviewerDto();
        reviewerDto.setId(reviewer.getId());
        reviewerDto.setName(reviewer.getName());
        return reviewerDto;
    }
}
