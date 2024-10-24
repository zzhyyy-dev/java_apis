package com.example.vritual.service;

import com.example.vritual.dto.SchoolClassDTO;
import com.example.vritual.dto.StudentDTO;
import com.example.vritual.entities.SchoolClass;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.SchoolClassRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    public List<SchoolClassDTO> getTeacherClasses(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + teacherId));

        List<SchoolClass> classes = schoolClassRepository.findByTeacher(teacher);

        return classes.stream()
                .map(schoolClass -> new SchoolClassDTO(
                        schoolClass.getId(),
                        schoolClass.getName(),
                        schoolClass.getDescription()
                ))
                .collect(Collectors.toList());
    }


    public List<StudentDTO> getStudentsByClass(Long classId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        List<Student> students = studentRepository.findBySchoolClass(schoolClass);

        return students.stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.isActive()
                ))
                .collect(Collectors.toList());
    }


    public StudentDTO changeStudentClass(Long studentId, Long newClassId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        SchoolClass newClass = schoolClassRepository.findById(newClassId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + newClassId));

        student.setSchoolClass(newClass);
        student.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(student);

        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.isActive());
    }


    public Map<String, Object> changeClassTeacher(Long classId, Long newTeacherId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        Teacher newTeacher = teacherRepository.findById(newTeacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + newTeacherId));

        schoolClass.setTeacher(newTeacher);
        schoolClass.setUpdatedAt(LocalDateTime.now());
        schoolClassRepository.save(schoolClass);

        return Map.of(
                "classId", schoolClass.getId(),
                "newTeacherId", newTeacher.getId(),
                "message", "Teacher changed successfully"
        );
    }
}
