package com.example.vritual.dto;// Updated DTO
import com.fasterxml.jackson.databind.JsonNode;

public class StudentChallengeDTO {
    private Long challengeId;
    private String challengeName;
    private String score;

    public StudentChallengeDTO(Long challengeId, String challengeName, String score) {
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.score = score;
    }

    // Getters and setters
    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(JsonNode score) {
        this.score = String.valueOf(score);
    }
}
