package com.example.vritual.model.repository;

import com.example.vritual.model.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findAllByTeacher_Id(Long studentId);
}
