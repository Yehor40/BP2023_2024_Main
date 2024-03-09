package com.example.bp2023_2024_Main.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detail_id;
    @OneToOne
    @JoinColumn(name = "task_id")  // Adjust the column name based on your actual database schema
    private Project_Task task;
    private String timeSpent;
    private String realTime;
    private String charge;
    private String total;
    private String state;
    private String month;

}