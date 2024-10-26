package com.example.vritual.dto;

import lombok.Data;

@Data
public class EvaluateStudentDTO {
    private Long studentId;
    private Long challengeId;
    private String score; // JSON String format for the score
}
