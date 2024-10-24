package com.example.vritual.dto;

public record AdministratorDTO(
        Long id,
        String name,
        String email,
        String password,
        boolean active
) {}
