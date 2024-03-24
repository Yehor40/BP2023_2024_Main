package com.example.bp2023_2024_Main.controller;

import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.service.EvidenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EvidenceController {
  private final EvidenceService evidenceService;


    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }
    @GetMapping("/evidences")
public String showAllEvidences(Model model){
        List<Evidence>evidences = evidenceService.getAllEvidence();
        model.addAttribute("evidences",evidences);
        return"evidences";
    }
    @GetMapping("/evidences/{id}")
    public String showEvidencebyId(@PathVariable Long id, Model model){
        Evidence evidence = evidenceService.getEvidenceById(id).orElseThrow();
        model.addAttribute("evidence",evidence);
        return"evidencesMore";
    }
}

