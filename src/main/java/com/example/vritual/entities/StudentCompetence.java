package com.example.vritual.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_competences")
@Data
public class StudentCompetence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competence_id", nullable = false)
    private Competence competence;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Integer score;
}
