package com.example.vritual.controller;

import com.example.vritual.dto.ChallengeSessionDTO;
import com.example.vritual.dto.SessionDTO;
import com.example.vritual.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDTO> createNewSession(@RequestBody SessionDTO sessionDTO) {
        SessionDTO createdSession = sessionService.createNewSession(sessionDTO);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ChallengeSessionDTO> readSessionChallenge(@PathVariable Long sessionId) {
        ChallengeSessionDTO sessionDTO = sessionService.readSessionChallenge(sessionId);
        return ResponseEntity.ok(sessionDTO);
    }
}