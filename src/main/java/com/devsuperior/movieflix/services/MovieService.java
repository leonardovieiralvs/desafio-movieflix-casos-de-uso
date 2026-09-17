package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDetailsDTO findById(Long id) {
        Movie resourceNotFound = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new MovieDetailsDTO(resourceNotFound);
    }

    public Page<MovieDetailsDTO> findById(Long id, Pageable pageable) {
        Page<Movie> result = movieRepository.findById(id, pageable);
        return result.map(MovieDetailsDTO::new);
    }

    public Page<MovieDetailsDTO> findAll(Long genreId, Pageable pageable) {
        Page<Movie> result = movieRepository.searchByGenre(genreId, pageable);
        return result.map(MovieDetailsDTO::new);
    }
}
