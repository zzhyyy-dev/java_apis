package com.example.vritual.dto;

import lombok.Data;

@Data
public class CreateChallengeDTO {
    private Long challengeSessionId;
    private String description;
    private Long teacherId;
    private String name;
}