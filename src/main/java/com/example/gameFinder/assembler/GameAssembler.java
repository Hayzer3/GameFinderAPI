package com.example.gameFinder.assembler;

import com.example.gameFinder.controller.GameController;
import com.example.gameFinder.dto.GameResponse;
import com.example.gameFinder.model.Game;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameAssembler implements RepresentationModelAssembler<Game, GameResponse> {

    @Override
    public GameResponse toModel(Game game) {
        GameResponse response = new GameResponse();
        BeanUtils.copyProperties(game, response);

        response.setGenre(game.getGenre());
        response.setPlatform(game.getPlatform());

        // Links HATEOAS
        response.add(linkTo(methodOn(GameController.class).findById(game.getId())).withSelfRel());

        response.add(linkTo(methodOn(GameController.class).findByGenre(game.getGenre().getId(), Pageable.unpaged()))
                .withRel("same-genre")
                .withTitle("Games in " + game.getGenre().getName() + " genre"));

        response.add(linkTo(methodOn(GameController.class).findByPlatform(game.getPlatform().getId(), Pageable.unpaged()))
                .withRel("same-platform")
                .withTitle("Games on " + game.getPlatform().getName()));

        return response;
    }
}