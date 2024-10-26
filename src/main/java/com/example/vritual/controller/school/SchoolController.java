package com.example.vritual.controller.school;

import com.example.vritual.controller.school.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/classes")
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        return ResponseEntity.ok(schoolService.getAllClasses());
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

    @PostMapping
    public ResponseEntity<ClassResponseDTO> createClass(@RequestBody CreateClassDTO createClassDTO) {
        ClassResponseDTO newClass = schoolService.createClass(createClassDTO);
        return new ResponseEntity<>(newClass, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        schoolService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
