package com.example.vritual.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateClassDTO {
    private String description;
    private String name;
    private Long teacherId;
}
