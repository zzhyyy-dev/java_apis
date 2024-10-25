package com.example.vritual.dto;

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

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getExerciseToolId() {
        return exerciseToolId;
    }

    public void setExerciseToolId(Long exerciseToolId) {
        this.exerciseToolId = exerciseToolId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getLeftToolId() {
        return leftToolId;
    }

    public void setLeftToolId(Long leftToolId) {
        this.leftToolId = leftToolId;
    }

    public String getLeftToolName() {
        return leftToolName;
    }

    public void setLeftToolName(String leftToolName) {
        this.leftToolName = leftToolName;
    }

    public Long getRightToolId() {
        return rightToolId;
    }

    public void setRightToolId(Long rightToolId) {
        this.rightToolId = rightToolId;
    }

    public String getRightToolName() {
        return rightToolName;
    }

    public void setRightToolName(String rightToolName) {
        this.rightToolName = rightToolName;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }
}
