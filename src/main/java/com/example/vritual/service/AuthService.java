package com.example.vritual.service;

import com.example.vritual.entities.Administrator;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.AdministratorRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public Long authenticateUser(String userType, String email, String password) {
        switch (userType.toLowerCase()) {
            case "student":
                return authenticateStudent(email, password);
            case "teacher":
                return authenticateTeacher(email, password);
            case "administrator":
                return authenticateAdministrator(email, password);
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    private Long authenticateStudent(String email, String password) {
        Optional<Student> studentOpt = studentRepository.findByEmail(email);

        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid email for student");
        }

        Student student = studentOpt.get();
        if (!student.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for student");
        }

        return student.getId();
    }

    private Long authenticateTeacher(String email, String password) {
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(email);

        if (teacherOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid email for teacher");
        }

        Teacher teacher = teacherOpt.get();
        if (!teacher.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for teacher");
        }

        return teacher.getId();
    }

    private Long authenticateAdministrator(String email, String password) {
        Optional<Administrator> adminOpt = administratorRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid email for administrator");
        }

        Administrator admin = adminOpt.get();
        if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for administrator");
        }

        return admin.getId();
    }
}
