package com.example.vritual.service;

import com.example.vritual.entities.Administrator;
import com.example.vritual.entities.Student;
import com.example.vritual.entities.Teacher;
import com.example.vritual.repository.AdministratorRepository;
import com.example.vritual.repository.StudentRepository;
import com.example.vritual.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    public Map<String, Object> authenticateUser(String email, String password) {
        // Tenta autenticar como Student
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return Map.of(
                    "userType", "student",
                    "id", student.get().getId()
            );
        }

        // Tenta autenticar como Teacher
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if (teacher.isPresent() && teacher.get().getPassword().equals(password)) {
            return Map.of(
                    "userType", "teacher",
                    "id", teacher.get().getId()
            );
        }

        // Tenta autenticar como Administrator
        Optional<Administrator> administrator = administratorRepository.findByEmail(email);
        if (administrator.isPresent() && administrator.get().getPassword().equals(password)) {
            return Map.of(
                    "userType", "administrator",
                    "id", administrator.get().getId()
            );
        }

        // Se nenhum usuário for encontrado, lança uma exceção
        throw new IllegalArgumentException("Invalid email or password");
    }
}
