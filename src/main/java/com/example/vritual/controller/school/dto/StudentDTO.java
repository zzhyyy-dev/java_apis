package com.example.vritual.controller.school.dto;

public record StudentDTO(
        Long id,
        String name,
        String email,
        boolean active
) {
}
