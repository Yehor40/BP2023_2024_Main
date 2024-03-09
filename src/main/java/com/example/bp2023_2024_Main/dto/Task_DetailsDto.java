package com.example.bp2023_2024_Main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task_DetailsDto {
    private String timeSpent;
    private String realTime;
    private String charge;
    private String total;
    private String state;
    private String month;

}
