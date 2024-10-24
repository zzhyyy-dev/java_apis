package com.example.vritual.repository;


import com.example.vritual.entities.ArcadeSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArcadeSessionRepository extends JpaRepository<ArcadeSession, Long> {
}
