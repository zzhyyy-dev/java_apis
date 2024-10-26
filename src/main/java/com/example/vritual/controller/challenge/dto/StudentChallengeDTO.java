package com.example.vritual.controller.challenge.dto;// Updated DTO
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StudentChallengeDTO {
    // Getters and setters
    @Setter
    private Long challengeId;
    @Setter
    private String challengeName;
    private String score;

    public StudentChallengeDTO(Long challengeId, String challengeName, String score) {
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.score = score;
    }

    public void setScore(JsonNode score) {
        this.score = String.valueOf(score);
    }
}
