package com.example.vritual.dto;

public record SchoolClassDTO(
        Long id,
        String description,
        String name,
        boolean active,
        Long teacherId
) {}
