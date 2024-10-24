package com.example.vritual.controller;

import com.example.vritual.dto.AuthResponseDTO;
import com.example.vritual.dto.LoginDTO;
import com.example.vritual.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        AuthResponseDTO response = authService.authenticateUser(loginDTO);
        return ResponseEntity.ok(response);
    }
}
