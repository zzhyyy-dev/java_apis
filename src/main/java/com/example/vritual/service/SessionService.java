package com.example.vritual.service;

import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.dto.SessionDTO;
import com.example.vritual.entities.ChallengeSession;
import com.example.vritual.repository.ChallengeSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private ChallengeSessionRepository challengeSessionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public SessionDTO createNewSession(SessionDTO sessionDTO) {
        ChallengeSession challengeSession = new ChallengeSession();
        challengeSession.setExercises(sessionDTO.getExercises());
        challengeSession.setDifficulty(sessionDTO.getDifficulty());
        challengeSession.setActive(sessionDTO.isActive());
        challengeSession.setCreatedAt(LocalDateTime.now());
        challengeSession.setUpdatedAt(LocalDateTime.now());

        ChallengeSession savedSession = challengeSessionRepository.save(challengeSession);
        sessionDTO.setId(savedSession.getId());
        sessionDTO.setCreatedAt(savedSession.getCreatedAt());
        sessionDTO.setUpdatedAt(savedSession.getUpdatedAt());

        return sessionDTO;
    }

    public ChallengeSessionDTO readSessionChallenge(Long sessionId) {
        ChallengeSession session = challengeSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge Session not found"));
        return new ChallengeSessionDTO(
                session.getExercises(),
                session.getDifficulty(),
                session.isActive()
        );
    }
}