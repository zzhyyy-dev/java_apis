package com.example.vritual.dto;

public record ExerciseDTO(
        Long id,
        String name,
        String description,
        boolean active,
        Long exerciseToolId,
        String difficulty
) {}
