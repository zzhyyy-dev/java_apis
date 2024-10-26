package com.example.vritual.controller.challenge;

import com.example.vritual.controller.challenge.dto.ChallengeDTO;
import com.example.vritual.controller.challenge.dto.CreateChallengeDTO;
import com.example.vritual.controller.challenge.dto.StudentChallengeDTO;
import com.example.vritual.model.entities.Challenge;
import com.example.vritual.model.entities.ChallengeSession;
import com.example.vritual.model.entities.Teacher;
import com.example.vritual.model.repository.ChallengeRepository;
import com.example.vritual.model.repository.ChallengeSessionRepository;
import com.example.vritual.model.repository.TeacherRepository;
import com.example.vritual.model.repository.ChallengeStudentRepository;
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

    public List<Challenge> getChallengesByTeacherId(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return challengeRepository.findAllByTeacher_Id(teacher.getId());
    }

    public List<StudentChallengeDTO> readChallengesByStudent(Long studentId) {
        return challengeStudentRepository.findAllByStudentId(studentId).stream()
                .map(challengeStudent -> {
                    Challenge challenge = challengeRepository.findById(challengeStudent.getChallenge().getId()).orElse(null);
                    return new StudentChallengeDTO(
                            challengeStudent.getChallenge().getId(),
                            challenge != null ? challenge.getName() : "Unknown Challenge",
                            challengeStudent.getScore()
                    );
                })
                .collect(Collectors.toList());
    }



    public Challenge createChallenge(CreateChallengeDTO createChallengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(createChallengeDTO.getName());
        challenge.setDescription(createChallengeDTO.getDescription());

        ChallengeSession challengeSession = challengeSessionRepository.findById(createChallengeDTO.getChallengeSessionId())
                .orElseThrow(() -> new RuntimeException("Challenge session not found"));
        challenge.setChallengeSession(challengeSession);

        Teacher teacher = teacherRepository.findById(createChallengeDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        challenge.setTeacher(teacher);

        return challengeRepository.save(challenge);
    }
}