package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }


    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping("{id}/reviews")
    public ResponseEntity<Page<MovieDetailsDTO>> findAllPaged(@PathVariable Long id, Pageable pageable) {

        return ResponseEntity.ok().body(movieService.findAll(id, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDetailsDTO>> findGenrePaged(@RequestParam(name = "genreId", defaultValue = "0") Long genreId,
                                                           Pageable pageable) {

        Page<MovieDetailsDTO> pageGenreId = movieService.findPageGenreId(genreId, pageable);
        return ResponseEntity.ok().body(pageGenreId);
    }
}
