package com.example.vritual.controller;

import com.example.vritual.dto.LoginDTO;
import com.example.vritual.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody LoginDTO loginDTO) {
        Long userId = authService.authenticateUser(
                loginDTO.userType(),
                loginDTO.email(),
                loginDTO.password()
        );

        return Map.of("userId", userId);
    }
}
