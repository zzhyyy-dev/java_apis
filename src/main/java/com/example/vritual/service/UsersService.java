package com.example.vritual.service;

import com.example.vritual.entities.Administrator;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.AdministratorRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    // Método para criar um usuário
    public Map<String, Object> createUser(String userType, Map<String, Object> userDetails) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = new Student();
            student.setName((String) userDetails.get("name"));
            student.setEmail((String) userDetails.get("email"));
            student.setPassword((String) userDetails.get("password"));
            student.setActive(true);
            student.setCreatedAt(LocalDateTime.now());
            student.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(student);

            return Map.of(
                    "id", student.getId(),
                    "message", "Student created successfully."
            );

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setName((String) userDetails.get("name"));
            teacher.setEmail((String) userDetails.get("email"));
            teacher.setPassword((String) userDetails.get("password"));
            teacher.setActive(true);
            teacher.setCreatedAt(LocalDateTime.now());
            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);

            return Map.of(
                    "id", teacher.getId(),
                    "message", "Teacher created successfully."
            );

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = new Administrator();
            administrator.setName((String) userDetails.get("name"));
            administrator.setEmail((String) userDetails.get("email"));
            administrator.setPassword((String) userDetails.get("password"));
            administrator.setActive(true);
            administrator.setCreatedAt(LocalDateTime.now());
            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);

            return Map.of(
                    "id", administrator.getId(),
                    "message", "Administrator created successfully."
            );

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    // Método para atualizar um usuário
    public Map<String, Object> updateUser(String userType, int id, Map<String, Object> userDetails) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            if (userDetails.containsKey("name")) student.setName((String) userDetails.get("name"));
            if (userDetails.containsKey("email")) student.setEmail((String) userDetails.get("email"));
            if (userDetails.containsKey("password")) student.setPassword((String) userDetails.get("password"));
            student.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(student);

            return Map.of(
                    "id", student.getId(),
                    "message", "Student updated successfully."
            );

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

            if (userDetails.containsKey("name")) teacher.setName((String) userDetails.get("name"));
            if (userDetails.containsKey("email")) teacher.setEmail((String) userDetails.get("email"));
            if (userDetails.containsKey("password")) teacher.setPassword((String) userDetails.get("password"));
            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);

            return Map.of(
                    "id", teacher.getId(),
                    "message", "Teacher updated successfully."
            );

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));

            if (userDetails.containsKey("name")) administrator.setName((String) userDetails.get("name"));
            if (userDetails.containsKey("email")) administrator.setEmail((String) userDetails.get("email"));
            if (userDetails.containsKey("password")) administrator.setPassword((String) userDetails.get("password"));
            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);

            return Map.of(
                    "id", administrator.getId(),
                    "message", "Administrator updated successfully."
            );

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    // Método para ler um usuário pelo ID
    public Map<String, Object> readUser(String userType, int id) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            return Map.of(
                    "id", student.getId(),
                    "name", student.getName(),
                    "email", student.getEmail(),
                    "active", student.isActive()
            );

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

            return Map.of(
                    "id", teacher.getId(),
                    "name", teacher.getName(),
                    "email", teacher.getEmail(),
                    "active", teacher.isActive()
            );

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));

            return Map.of(
                    "id", administrator.getId(),
                    "name", administrator.getName(),
                    "email", administrator.getEmail(),
                    "active", administrator.isActive()
            );

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    // Método para ler todos os usuários de um tipo específico
    public List<Map<String, Object>> readAllUsers(String userType) {
        if (userType.equalsIgnoreCase("student")) {
            return studentRepository.findAll().stream()
                    .map(student -> Map.<String, Object>of(
                            "id", student.getId(),
                            "name", student.getName(),
                            "email", student.getEmail(),
                            "active", student.isActive()
                    ))
                    .collect(Collectors.toList());

        } else if (userType.equalsIgnoreCase("teacher")) {
            return teacherRepository.findAll().stream()
                    .map(teacher -> Map.<String, Object>of(
                            "id", teacher.getId(),
                            "name", teacher.getName(),
                            "email", teacher.getEmail(),
                            "active", teacher.isActive()
                    ))
                    .collect(Collectors.toList());

        } else if (userType.equalsIgnoreCase("administrator")) {
            return administratorRepository.findAll().stream()
                    .map(admin -> Map.<String, Object>of(
                            "id", admin.getId(),
                            "name", admin.getName(),
                            "email", admin.getEmail(),
                            "active", admin.isActive()
                    ))
                    .collect(Collectors.toList());

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }


    // Método para deleção lógica de um usuário
    public Map<String, Object> deleteUser(String userType, int id) {
        if (userType.equalsIgnoreCase("student")) {
            Student student = studentRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            student.setActive(false);
            student.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(student);

            return Map.of(
                    "id", student.getId(),
                    "active", student.isActive()
            );

        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = teacherRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

            teacher.setActive(false);
            teacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(teacher);

            return Map.of(
                    "id", teacher.getId(),
                    "active", teacher.isActive()
            );

        } else if (userType.equalsIgnoreCase("administrator")) {
            Administrator administrator = administratorRepository.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));

            administrator.setActive(false);
            administrator.setUpdatedAt(LocalDateTime.now());
            administratorRepository.save(administrator);

            return Map.of(
                    "id", administrator.getId(),
                    "active", administrator.isActive()
            );

        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
