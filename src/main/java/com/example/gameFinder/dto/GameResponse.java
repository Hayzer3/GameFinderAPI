package com.example.gameFinder.dto;

import com.example.gameFinder.model.Genre;
import com.example.gameFinder.model.Platform;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "gameList", itemRelation = "game")
public class GameResponse extends RepresentationModel<GameResponse> {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Double rating;
    private Genre genre;
    private Platform platform;
    private String coverUrl;
    private String backdropUrl;
    private boolean inWishlist;
}