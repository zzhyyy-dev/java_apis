package com.example.vritual.dto;

public record StudentDTO(
        Long id,
        String name,
        String email,
        boolean active
) {
}
