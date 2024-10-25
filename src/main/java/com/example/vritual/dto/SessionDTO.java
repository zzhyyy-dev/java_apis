package com.example.vritual.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SessionDTO {
    private Long id;
    private String exercises;
    private String difficulty;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

@Data
class ExerciseOrderDTO {
    private int order;
    private Long exercisesId;
}
