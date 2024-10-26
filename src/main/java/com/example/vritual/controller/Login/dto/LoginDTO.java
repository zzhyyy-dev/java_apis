package com.example.vritual.controller.Login.dto;

public record LoginDTO(
        String userType,
        String email,
        String password
) {}