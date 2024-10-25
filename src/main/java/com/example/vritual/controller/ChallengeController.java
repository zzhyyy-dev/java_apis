package com.example.vritual.controller;

import com.example.vritual.dto.*;
import com.example.vritual.entities.Challenge;
import com.example.vritual.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;


    @PostMapping("/create")
    public ResponseEntity<Long> createChallenge(@RequestBody CreateChallengeDTO createChallengeDTO) {
        Challenge createdChallenge = challengeService.createChallenge(createChallengeDTO);
        return ResponseEntity.ok(createdChallenge.getId());
    }

    @GetMapping("/{challengeId}")
    public ResponseEntity<ChallengeDTO> readChallenge(@PathVariable Long challengeId) {
        ChallengeDTO challengeDTO = challengeService.readChallenge(challengeId);
        return ResponseEntity.ok(challengeDTO);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ChallengeResponseDTO>> getChallengesByTeacherId(@PathVariable Long teacherId) {
        List<Challenge> challenges = challengeService.getChallengesByTeacherId(teacherId);
        List<ChallengeResponseDTO> response = challenges.stream()
                .map(challenge -> new ChallengeResponseDTO(
                        challenge.getId(),
                        challenge.getName(),
                        challenge.getDescription(),
                        challenge.getChallengeSession().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentChallengeDTO>> readChallengesByStudent(@PathVariable Long studentId) {
        List<StudentChallengeDTO> challenges = challengeService.readChallengesByStudent(studentId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<ChallengeSessionDTO> readSessionChallenge(@PathVariable Long sessionId) {
        ChallengeSessionDTO sessionDTO = challengeService.readSessionChallenge(sessionId);
        return ResponseEntity.ok(sessionDTO);
    }
}
