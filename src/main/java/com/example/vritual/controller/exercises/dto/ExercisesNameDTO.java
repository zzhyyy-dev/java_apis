package com.example.vritual.controller.exercises.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExercisesNameDTO {
    private Integer exercisesId;
    private String name;
    private String difficulty;
    private Integer exerciseToolId;
    private String exerciseToolName;
    private String exerciseToolDescription;
    private String leftToolName;
    private String rightToolName;
    private String modifierName;

}