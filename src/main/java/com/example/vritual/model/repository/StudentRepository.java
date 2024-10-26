package com.example.vritual.model.repository;

import com.example.vritual.model.entities.SchoolClass;
import com.example.vritual.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);


    List<Student> findBySchoolClass(SchoolClass schoolClass);


    List<Student> findByActiveTrue();

    List<Student> findBySchoolClassAndActiveTrue(SchoolClass schoolClass);
}
