package com.example.gameFinder.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
