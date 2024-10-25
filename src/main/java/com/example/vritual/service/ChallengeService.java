package com.example.vritual.service;

import com.example.vritual.dto.ChallengeDTO;
import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.dto.StudentChallengeDTO;
import com.example.vritual.entities.Challenge;
import com.example.vritual.entities.ChallengeSession;
import com.example.vritual.entities.Teacher;
import com.example.vritual.entities.ChallengeStudent;
import com.example.vritual.repository.ChallengeRepository;
import com.example.vritual.repository.ChallengeSessionRepository;
import com.example.vritual.repository.TeacherRepository;
import com.example.vritual.repository.ChallengeStudentRepository;
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

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ChallengeStudentRepository challengeStudentRepository;

    public Long createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setDescription(challengeDTO.description());
        challenge.setActive(challengeDTO.active());
        challenge.setChallengeSession(challengeSessionRepository.findById(challengeDTO.challengeSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Challenge Session not found")));
        challenge.setTeacher(teacherRepository.findById(challengeDTO.teacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found")));
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
                challenge.getChallengeSession().getId(),
                challenge.getDescription(),
                challenge.isActive(),
                challenge.getTeacher().getId()
        );
    }

    public List<ChallengeDTO> readChallengesByTeacher(Long teacherId) {
        return challengeRepository.findAllByTeacher_Id(teacherId).stream()
                .map(challenge -> new ChallengeDTO(
                        challenge.getChallengeSession().getId(),
                        challenge.getDescription(),
                        challenge.isActive(),
                        challenge.getTeacher().getId()))
                .collect(Collectors.toList());
    }

    public List<StudentChallengeDTO> readChallengesByStudent(Long studentId) {
        return challengeStudentRepository.findAllByStudentId(studentId).stream()
                .map(challengeStudent -> new StudentChallengeDTO(
                        challengeStudent.getChallenge().getId(),
                        challengeStudent.getScore()))
                .collect(Collectors.toList());
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