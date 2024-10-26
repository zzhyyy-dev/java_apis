package com.example.vritual.controller.school;

import com.example.vritual.controller.school.dto.*;
import com.example.vritual.model.entities.SchoolClass;
import com.example.vritual.model.entities.Student;
import com.example.vritual.model.entities.Teacher;
import com.example.vritual.model.repository.SchoolClassRepository;
import com.example.vritual.model.repository.StudentRepository;
import com.example.vritual.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<ClassesDTO> getAllClasses() {
        return schoolClassRepository.findAll().stream().map(schoolClass -> {
            Teacher teacher = teacherRepository.findById(schoolClass.getTeacher().getId()).orElse(null);
            return new ClassesDTO(
                    schoolClass.getId(),
                    schoolClass.getDescription(),
                    schoolClass.getName(),
                    teacher != null ? teacher.getId() : null,
                    teacher != null ? teacher.getName() : null
            );
        }).collect(Collectors.toList());
    }

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

    public ClassResponseDTO createClass(CreateClassDTO createClassDTO) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(createClassDTO.getTeacherId());
        if (teacherOptional.isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }

        Teacher teacher = teacherOptional.get();

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName(createClassDTO.getName());
        schoolClass.setDescription(createClassDTO.getDescription());
        schoolClass.setTeacher(teacher);
        schoolClass.setCreatedAt(LocalDateTime.now());
        schoolClass.setUpdatedAt(LocalDateTime.now());

        SchoolClass savedClass = schoolClassRepository.save(schoolClass);

        ClassResponseDTO responseDTO = new ClassResponseDTO();
        responseDTO.setId(savedClass.getId());
        responseDTO.setTeacherId(teacher.getId());
        responseDTO.setName(savedClass.getName());
        responseDTO.setTeacherName(teacher.getName());

        return responseDTO;
    }

    public void deleteClass(Long id) {
        Optional<SchoolClass> schoolClassOptional = schoolClassRepository.findById(id);
        if (schoolClassOptional.isEmpty()) {
            throw new RuntimeException("School class not found");
        }

        SchoolClass schoolClass = schoolClassOptional.get();
        schoolClass.setActive(false);
        schoolClassRepository.save(schoolClass);
    }
}
