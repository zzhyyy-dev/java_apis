
package com.example.vritual.controller;

import com.example.vritual.dto.ChallengeDTO;
import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/create")
    public ResponseEntity<Long> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        Long challengeId = challengeService.createChallenge(challengeDTO);
        return ResponseEntity.ok(challengeId);
    }

    @PostMapping("/session/create")
    public ResponseEntity<Long> createSession(@RequestBody ChallengeSessionDTO challengeSessionDTO) {
        Long sessionId = challengeService.createSession(challengeSessionDTO);
        return ResponseEntity.ok(sessionId);
    }

    @GetMapping("/read/{challengeId}")
    public ResponseEntity<ChallengeDTO> readChallenge(@PathVariable Long challengeId) {
        ChallengeDTO challenge = challengeService.readChallenge(challengeId);
        return ResponseEntity.ok(challenge);
    }

    @GetMapping("/readMyChallenges/{userType}/{userId}")
    public ResponseEntity<List<Object>> readMyChallenges(@PathVariable String userType, @PathVariable Long userId) {
        List<Object> challenges = challengeService.readMyChallenges(userType, userId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/session/read/{sessionId}")
    public ResponseEntity<ChallengeSessionDTO> readSessionChallenge(@PathVariable Long sessionId) {
        ChallengeSessionDTO session = challengeService.readSessionChallenge(sessionId);
        return ResponseEntity.ok(session);
    }
}