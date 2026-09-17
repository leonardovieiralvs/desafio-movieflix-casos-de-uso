package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT obj FROM Movie obj WHERE (:genreId = 0 OR obj.genre.id = :genreId)")
    Page<Movie> searchByGenre(Long genreId, Pageable pageable);

    Page<Movie> findById(Long genreId, Pageable pageable);
}
