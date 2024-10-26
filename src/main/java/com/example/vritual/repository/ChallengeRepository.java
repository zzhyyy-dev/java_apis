package com.example.vritual.repository;

import com.example.vritual.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findAllByTeacher_Id(Long studentId);
}
