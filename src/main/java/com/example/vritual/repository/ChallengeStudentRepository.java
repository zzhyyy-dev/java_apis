package com.example.vritual.repository;

import com.example.vritual.entities.ChallengeStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChallengeStudentRepository extends JpaRepository<ChallengeStudent, Long> {
    List<ChallengeStudent> findAllByStudentId(Long studentId);
}
