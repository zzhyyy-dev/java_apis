package com.example.vritual.controller;

import com.example.vritual.dto.ChangeClassTeacherDTO;
import com.example.vritual.dto.ChangeStudentClassDTO;
import com.example.vritual.dto.SchoolClassDTO;
import com.example.vritual.dto.StudentDTO;
import com.example.vritual.dto.UserDTO;
import com.example.vritual.dto.UserReadDTO;
import com.example.vritual.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/{userType}")
    public ResponseEntity<UserReadDTO> createUser(@PathVariable String userType, @RequestBody UserDTO userDTO) {
        UserReadDTO response = usersService.createUser(
                userType,
                userDTO
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userType}/{id}")
    public ResponseEntity<UserReadDTO> updateUser(@PathVariable String userType, @PathVariable int id, @RequestBody UserDTO userDTO) {
        UserReadDTO response = usersService.updateUser(
                userType,
                id,
                userDTO
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userType}/{id}")
    public ResponseEntity<UserReadDTO> readUser(@PathVariable String userType, @PathVariable int id) {
        UserReadDTO response = usersService.readUser(
                userType,
                id
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userType}")
    public ResponseEntity<List<UserReadDTO>> readAllUsers(@PathVariable String userType) {
        List<UserReadDTO> response = usersService.readAllUsers(userType);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userType}/{id}/deactivate")
    public ResponseEntity<UserReadDTO> deleteUser(@PathVariable String userType, @PathVariable int id) {
        UserReadDTO response = usersService.deleteUser(
                userType,
                id
        );
        return ResponseEntity.ok(response);
    }
}
