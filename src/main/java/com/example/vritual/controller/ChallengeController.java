package com.example.vritual.controller;

import com.example.vritual.dto.ChallengeDTO;
import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.dto.StudentChallengeDTO;
import com.example.vritual.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/create")
    public ResponseEntity<Long> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        Long challengeId = challengeService.createChallenge(challengeDTO);
        return ResponseEntity.ok(challengeId);
    }



    @GetMapping("/{challengeId}")
    public ResponseEntity<ChallengeDTO> readChallenge(@PathVariable Long challengeId) {
        ChallengeDTO challengeDTO = challengeService.readChallenge(challengeId);
        return ResponseEntity.ok(challengeDTO);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ChallengeDTO>> readChallengesByTeacher(@PathVariable Long teacherId) {
        List<ChallengeDTO> challenges = challengeService.readChallengesByTeacher(teacherId);
        return ResponseEntity.ok(challenges);
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
