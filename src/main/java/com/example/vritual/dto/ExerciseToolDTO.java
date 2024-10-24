package com.example.vritual.dto;

public record ExerciseToolDTO(
        Long id,
        Long leftToolId,
        Long rightToolId,
        Long modifierId
) {}
