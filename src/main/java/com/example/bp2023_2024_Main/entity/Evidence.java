package com.example.bp2023_2024_Main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String project;
    private String orderNum;
    private String department;
    private double estTime;
    private double timeSpent;
    private double charge;
    private double total;
    private String state;
    private String month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

