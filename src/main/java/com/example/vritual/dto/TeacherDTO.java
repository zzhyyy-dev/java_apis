package com.example.vritual.dto;

public record TeacherDTO(
        Long id,
        String name,
        String email,
        String password,
        boolean active
) {}
