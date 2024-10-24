package com.example.vritual.entities;



import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Este é o campo que estava faltando

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "exercise_tool_id", nullable = false)
    private ExerciseTool exerciseTool;

    @Column(nullable = false, length = 50)
    private String difficulty = "beginner";

    // Getters and setters omitted for brevity
}
