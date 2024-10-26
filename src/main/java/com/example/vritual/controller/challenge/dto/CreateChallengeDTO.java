package com.example.vritual.controller.challenge.dto;

import lombok.Data;

@Data
public class CreateChallengeDTO {
    private Long challengeSessionId;
    private String description;
    private Long teacherId;
    private String name;
}