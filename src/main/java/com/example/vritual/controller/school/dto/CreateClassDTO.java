package com.example.vritual.controller.school.dto;

import lombok.Data;

@Data
public class CreateClassDTO {
    private String description;
    private String name;
    private Long teacherId;
}
