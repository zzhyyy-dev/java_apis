package com.example.vritual.controller.exercises;
import com.example.vritual.controller.exercises.dto.ExerciseDTO;
import com.example.vritual.controller.exercises.dto.ExercisesNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:5173")
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
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getLeftTool().getName() : null,
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getRightTool().getId() : null,
                        exercise.getExerciseTool() != null ? exercise.getExerciseTool().getRightTool().getName() : null,
                        exercise.getExerciseTool() != null && exercise.getExerciseTool().getModifier() != null ? exercise.getExerciseTool().getModifier().getId() : null,
                        exercise.getExerciseTool() != null && exercise.getExerciseTool().getModifier() != null ? exercise.getExerciseTool().getModifier().getName() : null
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/get-names")
    public List<ExercisesNameDTO> getExerciseNames(@RequestParam List<Integer> exerciseIds) {
        return exerciseService.getExercisesWithNames(exerciseIds);
    }
}