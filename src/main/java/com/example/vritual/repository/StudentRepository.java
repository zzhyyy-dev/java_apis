package com.example.vritual.repository;


import com.example.vritual.entities.SchoolClass;
import com.example.vritual.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    List<Student> findBySchoolClass(SchoolClass schoolClass);

}
