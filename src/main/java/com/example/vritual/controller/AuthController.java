package com.example.vritual.controller;

import com.example.vritual.dto.LoginDTO;
import com.example.vritual.dto.StudentAuthResponseDTO;
import com.example.vritual.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody LoginDTO loginDTO) {
        Object authResponse = authService.authenticateUser(
                loginDTO.userType(),
                loginDTO.email(),
                loginDTO.password()
        );

        if (authResponse instanceof StudentAuthResponseDTO studentAuthResponse) {
            return Map.of(
                    "userId", studentAuthResponse.id(),
                    "classId", studentAuthResponse.classId()
            );
        } else {
            return Map.of("userId", authResponse);
        }
    }
}
