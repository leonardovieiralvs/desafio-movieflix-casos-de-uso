package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDTO insert(ReviewDTO reviewDTO) {
        Movie movie = new Movie();
        movie.setId(reviewDTO.getMovieId());

        Review review = new Review();
        review.setText(reviewDTO.getText());
        review.setMovie(movie);

        Review saveReview = reviewRepository.save(review);

        return new ReviewDTO(saveReview);
    }

}
