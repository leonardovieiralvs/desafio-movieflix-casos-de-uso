package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewService reviewService;

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping()
    public ResponseEntity<ReviewDTO> insert(@RequestBody @Valid ReviewDTO reviewDTO) {
        reviewService.insert(reviewDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


}
