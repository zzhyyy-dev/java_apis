package com.example.vritual.controller.competences;

import com.example.vritual.model.entities.StudentCompetence;
import com.example.vritual.model.repository.StudentCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCompetenceService {

    @Autowired
    private StudentCompetenceRepository studentCompetenceRepository;

    public List<StudentCompetenceDTO> getStudentCompetencesById(Long studentId) {
        List<StudentCompetence> studentCompetences = studentCompetenceRepository.findByStudentId(studentId);
        return studentCompetences.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private StudentCompetenceDTO convertToDTO(StudentCompetence studentCompetence) {
        StudentCompetenceDTO dto = new StudentCompetenceDTO();
        dto.setId(studentCompetence.getId());
        dto.setCompetenceName(studentCompetence.getCompetence().getName());
        dto.setStudentId(studentCompetence.getStudent().getId());
        dto.setScore(studentCompetence.getScore());
        return dto;
    }
}