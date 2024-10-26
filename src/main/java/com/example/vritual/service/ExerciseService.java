package com.example.vritual.service;


import com.example.vritual.dto.ExercisesNameDTO;
import com.example.vritual.entities.Exercise;
import com.example.vritual.entities.ExerciseTool;
import com.example.vritual.entities.Modifier;
import com.example.vritual.entities.Tool;
import com.example.vritual.repository.ExerciseRepository;
import com.example.vritual.repository.ExerciseToolRepository;
import com.example.vritual.repository.ModifierRepository;
import com.example.vritual.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ExercisesNameDTO> getExercisesWithNames(List<Integer> exerciseIds) {
        return exerciseIds.stream().map(exerciseId -> {
            Exercise exerciseEntity = exerciseRepository.findById(exerciseId).orElse(null);
            ExercisesNameDTO dto = new ExercisesNameDTO();
            if (exerciseEntity != null) {
                dto.setExercisesId(exerciseId);
                dto.setName(exerciseEntity.getName());
                dto.setDifficulty(exerciseEntity.getDifficulty());

                Optional<ExerciseTool> exerciseTool = exerciseToolRepository.findById(exerciseEntity.getExerciseTool().getId());
                exerciseTool.ifPresent(tool -> {
                    dto.setExerciseToolId(Math.toIntExact(tool.getId()));



                    if (tool.getLeftTool() != null) {
                        tool.setLeftTool(toolRepository.findById(tool.getLeftTool().getId()).orElse(null));
                        dto.setLeftToolName(tool.getLeftTool() != null ? tool.getLeftTool().getName() : null);
                    }

                    if (tool.getRightTool() != null) {
                        tool.setRightTool(toolRepository.findById(tool.getRightTool().getId()).orElse(null));
                        dto.setRightToolName(tool.getRightTool() != null ? tool.getRightTool().getName() : null);
                    }

                    if (tool.getModifier() != null) {
                        tool.setModifier(modifierRepository.findById(tool.getModifier().getId()).orElse(null));
                        dto.setModifierName(tool.getModifier() != null ? tool.getModifier().getName() : null);
                    }
                });
            }
            return dto;
        }).collect(Collectors.toList());
    }
}