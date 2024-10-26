package com.example.vritual.dto;

import lombok.Data;

@Data
public class StudentCompetenceDTO {
    private Long id;
    private String competenceName;
    private Long studentId;
    private Integer score;
}