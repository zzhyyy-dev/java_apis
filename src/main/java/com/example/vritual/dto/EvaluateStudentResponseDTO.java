package com.example.vritual.dto;

import lombok.Data;

@Data
public class EvaluateStudentResponseDTO {
    private Long id;
    private Long studentId;
    private Long challengeId;
    private String score;
}
