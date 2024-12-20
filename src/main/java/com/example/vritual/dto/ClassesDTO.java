package com.example.vritual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassesDTO {
    private Long id;
    private String description;
    private String name;
    private Long teacherId;
    private String teacherName;
}
