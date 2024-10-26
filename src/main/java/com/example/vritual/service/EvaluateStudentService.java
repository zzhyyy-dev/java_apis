package com.example.vritual.service;

//import com.example.vritual.dto.EvaluateStudentDTO;
import com.example.vritual.dto.EvaluateStudentDTO;
import com.example.vritual.dto.EvaluateStudentResponseDTO;
import com.example.vritual.entities.Challenge;
import com.example.vritual.entities.ChallengeStudent;
import com.example.vritual.entities.Student;
//import com.example.vritual.repositories.ChallengeRepository;
//import com.example.vritual.repositories.ChallengeStudentRepository;
//import com.example.vritual.repositories.StudentRepository;
import com.example.vritual.repository.ChallengeRepository;
import com.example.vritual.repository.ChallengeStudentRepository;
import com.example.vritual.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EvaluateStudentService {

    @Autowired
    private ChallengeStudentRepository challengeStudentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    public EvaluateStudentResponseDTO evaluateStudent(EvaluateStudentDTO evaluateStudentDTO) {
        Student student = studentRepository.findById(evaluateStudentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Challenge challenge = challengeRepository.findById(evaluateStudentDTO.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found"));

        ChallengeStudent challengeStudent = new ChallengeStudent();
        challengeStudent.setStudent(student);
        challengeStudent.setChallenge(challenge);
        challengeStudent.setScore(evaluateStudentDTO.getScore());
        challengeStudent.setCreatedAt(LocalDateTime.now());
        challengeStudent.setUpdatedAt(LocalDateTime.now());

        ChallengeStudent savedChallengeStudent = challengeStudentRepository.save(challengeStudent);

        EvaluateStudentResponseDTO responseDTO = new EvaluateStudentResponseDTO();
        responseDTO.setId(savedChallengeStudent.getId());
        responseDTO.setStudentId(savedChallengeStudent.getStudent().getId());
        responseDTO.setChallengeId(savedChallengeStudent.getChallenge().getId());
        responseDTO.setScore(savedChallengeStudent.getScore());

        return responseDTO;
    }
}
