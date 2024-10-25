package com.example.vritual.service;


import com.example.vritual.entities.Exercise;
import com.example.vritual.entities.ExerciseTool;
import com.example.vritual.repository.ExerciseRepository;
import com.example.vritual.repository.ExerciseToolRepository;
import com.example.vritual.repository.ModifierRepository;
import com.example.vritual.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseToolRepository exerciseToolRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ModifierRepository modifierRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> getAllExercisesWithToolsAndModifiers() {
        List<Exercise> exercises = exerciseRepository.findAll();
        exercises.forEach(exercise -> {
            Optional<ExerciseTool> exerciseTool = exerciseToolRepository.findById(exercise.getExerciseTool().getId());
            exerciseTool.ifPresent(tool -> {
                exercise.setExerciseTool(tool);
                tool.setLeftTool(toolRepository.findById(tool.getLeftTool().getId()).orElse(null));
                tool.setRightTool(toolRepository.findById(tool.getRightTool().getId()).orElse(null));
                if (tool.getModifier() != null) {
                    tool.setModifier(modifierRepository.findById(tool.getModifier().getId()).orElse(null));
                }
            });
        });
        return exercises;
    }
}