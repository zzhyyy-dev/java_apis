package com.example.vritual.dto;

public record LoginDTO(
        String userType,
        String email,
        String password
) {}