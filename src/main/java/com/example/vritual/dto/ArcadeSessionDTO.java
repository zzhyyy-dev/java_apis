package com.example.vritual.dto;

public record ArcadeSessionDTO(
        Long id,
        String exercises,  // JSON data as String
        String difficulty,
        boolean active
) {}
