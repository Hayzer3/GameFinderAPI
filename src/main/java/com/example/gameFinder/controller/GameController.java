package com.example.gameFinder.controller;

import com.example.gameFinder.assembler.GameAssembler;
import com.example.gameFinder.dto.GameResponse;
import com.example.gameFinder.model.Game;
import com.example.gameFinder.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService service;

    @Autowired
    private GameAssembler gameAssembler;

    @Autowired
    private PagedResourcesAssembler<Game> pagedAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<GameResponse>> findAll(Pageable pageable) {
        Page<Game> games = service.findAll(pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(games, gameAssembler));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> findById(@PathVariable Long id) {
        GameResponse game = service.findById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/genres/{genreId}")
    public ResponseEntity<PagedModel<GameResponse>> findByGenre(
            @PathVariable Long genreId,
            Pageable pageable) {
        Page<Game> games = service.findByGenre(genreId, pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(games, gameAssembler));
    }

    @GetMapping("/platforms/{platformId}")
    public ResponseEntity<PagedModel<GameResponse>> findByPlatform(
            @PathVariable Long platformId,
            Pageable pageable) {
        Page<Game> games = service.findByPlatform(platformId, pageable);
        return ResponseEntity.ok(pagedAssembler.toModel(games, gameAssembler));
    }

    @PostMapping("/wishlist/{id}")
    public ResponseEntity<Void> addToWishlist(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/wishlist/{id}/remove")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}