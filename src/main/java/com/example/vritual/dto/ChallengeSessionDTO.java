package com.example.vritual.dto;

public record ChallengeSessionDTO(
        Long id,
        String exercises,  // JSON data as String
        String difficulty,
        boolean active
) {}

