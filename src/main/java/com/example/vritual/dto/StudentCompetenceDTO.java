package com.example.vritual.dto;

public record StudentCompetenceDTO(
        Long id,
        Long competenceId,
        Long studentId,
        Integer score
) {}
