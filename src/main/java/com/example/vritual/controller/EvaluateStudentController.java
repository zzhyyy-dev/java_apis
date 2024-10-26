package com.example.vritual.controller;

import com.example.vritual.dto.EvaluateStudentDTO;
import com.example.vritual.dto.EvaluateStudentResponseDTO;
//import com.example.vritual.services.EvaluateStudentService;
import com.example.vritual.service.EvaluateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluateStudentController {

    @Autowired
    private EvaluateStudentService evaluateStudentService;

    @PostMapping("/evaluate")
    public ResponseEntity<EvaluateStudentResponseDTO> evaluateStudent(@RequestBody EvaluateStudentDTO evaluateStudentDTO) {
        EvaluateStudentResponseDTO response = evaluateStudentService.evaluateStudent(evaluateStudentDTO);
        return ResponseEntity.ok(response);
    }
}
