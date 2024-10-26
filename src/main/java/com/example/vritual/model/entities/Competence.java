package com.example.vritual.model.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "competences")
@Data
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;
}
