package com.example.vritual.controller.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChallengeResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long challengeSessionId;

}