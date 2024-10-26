package com.example.vritual.repository;

import com.example.vritual.entities.StudentCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCompetenceRepository extends JpaRepository<StudentCompetence, Long> {
    List<StudentCompetence> findByStudentId(Long studentId);
}