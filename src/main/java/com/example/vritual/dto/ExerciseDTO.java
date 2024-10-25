// DTO
package com.example.vritual.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ExerciseDTO {
    @Setter
    private Integer id;
    @Setter
    private String name;
    private String description;
    @Setter
    private boolean active;
    @Setter
    private Long exerciseToolId;
    private String difficulty;
    @Setter
    private Long leftToolId;
    @Setter
    private Long rightToolId;
    @Setter
    private Long modifierId;

    public ExerciseDTO(Integer id, String name, String description, boolean active, Long exerciseToolId, String difficulty, Long leftToolId, Long rightToolId, Long modifierId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.exerciseToolId = exerciseToolId;
        this.difficulty = difficulty;
        this.leftToolId = leftToolId;
        this.rightToolId = rightToolId;
        this.modifierId = modifierId;
    }


}