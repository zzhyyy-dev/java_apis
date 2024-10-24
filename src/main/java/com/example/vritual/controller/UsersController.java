package com.example.vritual.controller;

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
    public ResponseEntity<Map<String, Object>> createUser(@PathVariable String userType, @RequestBody Map<String, Object> userDetails) {
        Map<String, Object> response = usersService.createUser(userType, userDetails);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{userType}/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String userType, @PathVariable int id, @RequestBody Map<String, Object> userDetails) {
        Map<String, Object> response = usersService.updateUser(userType, id, userDetails);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userType}/{id}")
    public ResponseEntity<Map<String, Object>> readUser(@PathVariable String userType, @PathVariable int id) {
        Map<String, Object> response = usersService.readUser(userType, id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{userType}")
    public ResponseEntity<List<Map<String, Object>>> readAllUsers(@PathVariable String userType) {
        List<Map<String, Object>> response = usersService.readAllUsers(userType);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{userType}/{id}/deactivate")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String userType, @PathVariable int id) {
        Map<String, Object> response = usersService.deleteUser(userType, id);
        return ResponseEntity.ok(response);
    }
}
