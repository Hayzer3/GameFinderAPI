package com.example.gameFinder.repository;

import com.example.gameFinder.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findByGenreId(Long genreId, Pageable pageable);

    Page<Game> findByPlatformId(Long platformId, Pageable pageable);
}