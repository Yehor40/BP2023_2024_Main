package com.example.bp2023_2024_Main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project_TaskDto {
    private Long taskId;
    private String taskName;
    private String department;
}
