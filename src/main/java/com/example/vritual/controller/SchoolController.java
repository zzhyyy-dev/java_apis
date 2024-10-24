package com.example.vritual.controller;

import com.example.vritual.dto.SchoolClassDTO;
import com.example.vritual.dto.StudentDTO;
import com.example.vritual.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @GetMapping("/teacher/{teacherId}/classes")
    public ResponseEntity<List<SchoolClassDTO>> getTeacherClasses(@PathVariable Long teacherId) {
        List<SchoolClassDTO> response = schoolService.getTeacherClasses(teacherId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/class/{classId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByClass(@PathVariable Long classId) {
        List<StudentDTO> response = schoolService.getStudentsByClass(classId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/students/{studentId}/changeClass/{newClassId}")
    public ResponseEntity<StudentDTO> changeStudentClass(@PathVariable Long studentId, @PathVariable Long newClassId) {
        StudentDTO response = schoolService.changeStudentClass(studentId, newClassId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/classes/{classId}/changeTeacher/{newTeacherId}")
    public ResponseEntity<Map<String, Object>> changeClassTeacher(@PathVariable Long classId, @PathVariable Long newTeacherId) {
        Map<String, Object> response = schoolService.changeClassTeacher(classId, newTeacherId);
        return ResponseEntity.ok(response);
    }
}
