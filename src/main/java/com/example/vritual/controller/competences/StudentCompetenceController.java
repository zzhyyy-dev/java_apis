package com.example.vritual.controller.competences;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/student-competences")
public class StudentCompetenceController {

    @Autowired
    private StudentCompetenceService studentCompetenceService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentCompetenceDTO>> getStudentCompetences(@PathVariable Long studentId) {
        List<StudentCompetenceDTO> competences = studentCompetenceService.getStudentCompetencesById(studentId);
        return ResponseEntity.ok(competences);
    }
}