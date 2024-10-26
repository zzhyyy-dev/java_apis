package com.example.vritual.model.repository;


import com.example.vritual.model.entities.ArcadeSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArcadeSessionRepository extends JpaRepository<ArcadeSession, Long> {
}
