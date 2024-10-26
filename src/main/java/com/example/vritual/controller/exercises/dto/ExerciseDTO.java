package com.example.vritual.controller.exercises.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseDTO {
    private Integer id;
    private String name;
    private String description;
    private boolean active;
    private Long exerciseToolId;
    private String difficulty;
    private Long leftToolId;
    private String leftToolName;
    private Long rightToolId;
    private String rightToolName;
    private Long modifierId;
    private String modifierName;

    public ExerciseDTO(Integer id, String name, String description, boolean active, Long exerciseToolId, String difficulty, Long leftToolId, String leftToolName, Long rightToolId, String rightToolName, Long modifierId, String modifierName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.exerciseToolId = exerciseToolId;
        this.difficulty = difficulty;
        this.leftToolId = leftToolId;
        this.leftToolName = leftToolName;
        this.rightToolId = rightToolId;
        this.rightToolName = rightToolName;
        this.modifierId = modifierId;
        this.modifierName = modifierName;
    }

}
