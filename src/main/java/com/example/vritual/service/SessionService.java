package com.example.vritual.service;

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

    public Optional<SessionDTO> getSessionById(Long id) {
        Optional<ChallengeSession> sessionOptional = challengeSessionRepository.findById(id);
        if (sessionOptional.isPresent()) {
            ChallengeSession challengeSession = sessionOptional.get();
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setId(challengeSession.getId());
            sessionDTO.setExercises(challengeSession.getExercises());
            sessionDTO.setDifficulty(challengeSession.getDifficulty());
            sessionDTO.setActive(challengeSession.isActive());
            sessionDTO.setCreatedAt(challengeSession.getCreatedAt());
            sessionDTO.setUpdatedAt(challengeSession.getUpdatedAt());
            sessionDTO.setDeletedAt(challengeSession.getDeletedAt());
            return Optional.of(sessionDTO);
        } else {
            return Optional.empty();
        }
    }
}