package com.example.vritual.dto;

public record StudentDTO(
        String name,
        String email,
        String registrationNumber,
        String course,
        Integer classId
) {
}
