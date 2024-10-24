package com.example.vritual.service;

import com.example.vritual.dto.ChallengeDTO;
import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.entities.Challenge;
import com.example.vritual.entities.ChallengeSession;
import com.example.vritual.repository.ChallengeRepository;
import com.example.vritual.repository.ChallengeSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeSessionRepository challengeSessionRepository;

    public Long createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setDescription(challengeDTO.description());
        challenge.setTeacherId(challengeDTO.teacherId());
        challenge.setActive(challengeDTO.active());
        Challenge savedChallenge = challengeRepository.save(challenge);
        return savedChallenge.getId();
    }

    public Long createSession(ChallengeSessionDTO challengeSessionDTO) {
        ChallengeSession session = new ChallengeSession();
        session.setExercises(challengeSessionDTO.exercises());
        session.setDifficulty(challengeSessionDTO.difficulty());
        session.setActive(challengeSessionDTO.active());
        ChallengeSession savedSession = challengeSessionRepository.save(session);
        return savedSession.getId();
    }

    public ChallengeDTO readChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found"));
        return new ChallengeDTO(
                challenge.getChallengeSessionId(),
                challenge.getDescription(),
                challenge.getActive(),
                challenge.getTeacherId()
        );
    }

    public List<Object> readMyChallenges(String userType, Long userId) {
        if (userType.equalsIgnoreCase("teacher")) {
            return challengeRepository.findByTeacherId(userId).stream()
                    .map(challenge -> new ChallengeDTO(
                            challenge.getChallengeSessionId(),
                            challenge.getDescription(),
                            challenge.getActive(),
                            challenge.getTeacherId()))
                    .collect(Collectors.toList());
        } else if (userType.equalsIgnoreCase("student")) {
            return challengeRepository.findByStudentId(userId).stream()
                    .map(challenge -> new StudentChallengeDTO(
                            challenge.getId(),
                            challenge.getScore()))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    public ChallengeSessionDTO readSessionChallenge(Long sessionId) {
        ChallengeSession session = challengeSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge Session not found"));
        return new ChallengeSessionDTO(
                session.getExercises(),
                session.getDifficulty(),
                session.getActive()
        );
    }
}
