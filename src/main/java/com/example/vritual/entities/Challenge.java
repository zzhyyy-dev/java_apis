package com.example.vritual.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "challenge")
@Data
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "challenge_session_id")
    private ChallengeSession challengeSession;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
