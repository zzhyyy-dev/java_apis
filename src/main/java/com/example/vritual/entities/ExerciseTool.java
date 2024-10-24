package com.example.vritual.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exercises_tools")
@Data
public class ExerciseTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "left_tool_id", nullable = false)
    private Tool leftTool;

    @ManyToOne
    @JoinColumn(name = "right_tool_id", nullable = false)
    private Tool rightTool;

    @ManyToOne
    @JoinColumn(name = "modifier_id")
    private Modifier modifier;
}
