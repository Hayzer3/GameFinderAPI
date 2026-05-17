package com.example.gameFinder.service;

import com.example.gameFinder.assembler.GameAssembler;
import com.example.gameFinder.dto.GameResponse;
import com.example.gameFinder.model.Game;
import com.example.gameFinder.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    @Autowired
    private GameAssembler assembler;

    public Page<Game> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public GameResponse findById(Long id) {
        Game game = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        return assembler.toModel(game);
    }

    // 3. Filtro por Gênero retornando Page<Game>
    public Page<Game> findByGenre(Long genreId, Pageable pageable) {
        return repository.findByGenreId(genreId, pageable);
    }

    //  Filtro por Plataforma retornando Page<Game>
    public Page<Game> findByPlatform(Long platformId, Pageable pageable) {
        return repository.findByPlatformId(platformId, pageable);
    }
}