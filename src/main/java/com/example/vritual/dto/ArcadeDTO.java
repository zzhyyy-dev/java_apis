package com.example.vritual.dto;

public record ArcadeDTO(
        Long id,
        boolean active,
        String finalScore,  // JSON data as String
        Long arcadeSessionId,
        Long studentId
) {}
