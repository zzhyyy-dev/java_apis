package com.example.vritual.service;


import com.example.vritual.entities.Administrator;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.AdministratorRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    // Autentica o usuário e retorna o ID ou mensagem de erro
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

    // Autenticar estudante
    private Long authenticateStudent(String email, String password) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email for student"));

        if (!student.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for student");
        }

        return student.getId();
    }


    private Long authenticateTeacher(String email, String password) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email for teacher"));

        if (!teacher.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for teacher");
        }

        return teacher.getId();
    }

    // Autenticar administrador
    private Long authenticateAdministrator(String email, String password) {
        Administrator admin = administratorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email for administrator"));

        if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password for administrator");
        }

        return admin.getId();
    }
}
