package com.example.vritual.controller;

import com.example.vritual.dto.ChangeClassTeacherDTO;
import com.example.vritual.dto.ChangeStudentClassDTO;
import com.example.vritual.dto.SchoolClassDTO;
import com.example.vritual.dto.StudentDTO;
import com.example.vritual.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
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

    @PutMapping("/students/changeClass")
    public ResponseEntity<StudentDTO> changeStudentClass(@RequestBody ChangeStudentClassDTO changeStudentClassDTO) {
        StudentDTO response = schoolService.changeStudentClass(
                changeStudentClassDTO.studentId(),
                changeStudentClassDTO.newClassId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/classes/changeTeacher")
    public ResponseEntity<Map<String, Object>> changeClassTeacher(@RequestBody ChangeClassTeacherDTO changeClassTeacherDTO) {
        Map<String, Object> response = schoolService.changeClassTeacher(
                changeClassTeacherDTO.classId(),
                changeClassTeacherDTO.newTeacherId()
        );
        return ResponseEntity.ok(response);
    }
}
