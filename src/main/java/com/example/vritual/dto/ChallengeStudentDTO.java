package com.example.vritual.dto;

public record ChallengeStudentDTO(
        Long id,
        Long challengeId,
        Long studentId,
        String score  // JSON data as String
) {}
