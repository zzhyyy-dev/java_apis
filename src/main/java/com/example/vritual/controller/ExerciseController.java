package com.example.vritual.controller;

import com.example.vritual.dto.ExerciseDTO;
//import com.example.vritual.dto.ExerciseToolDTO;

import com.example.vritual.entities.ExerciseTool;
import com.example.vritual.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseDTO> getAllExercisesWithToolsAndModifiers() {
        return exerciseService.getAllExercisesWithToolsAndModifiers().stream()
                .map(exercise -> new ExerciseDTO(
                        exercise.getId(),
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.isActive(),
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getId() : null,
                        exercise.getDifficulty(),
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getLeftTool().getId() : null,
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getRightTool().getId() : null,
                        exercise.getExerciseTool() != null && exercise.getExerciseTool().getModifier() != null ? exercise.getExerciseTool().getModifier().getId() : null
                ))
                .collect(Collectors.toList());
    }
}