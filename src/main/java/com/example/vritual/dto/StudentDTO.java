package com.example.vritual.dto;

public record StudentDTO(
        Long id,
        String name,
        String email,
        String password,
        boolean active,
        Long classId
) {}
