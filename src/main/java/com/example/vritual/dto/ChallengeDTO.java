package com.example.vritual.dto;

public record ChallengeDTO(
        Long id,
        String description,
        boolean active,
        Long challengeSessionId,
        Long teacherId
) {}
