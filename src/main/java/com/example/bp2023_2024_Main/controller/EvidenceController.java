package com.example.bp2023_2024_Main.controller;
import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.service.EvidenceService;
import com.example.bp2023_2024_Main.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
@Controller
public class EvidenceController {
  private final EvidenceService evidenceService;
    @Autowired
    private UserService userService;
    public EvidenceController(EvidenceService evidenceService, HttpServletRequest httpServletRequest) {
        this.evidenceService = evidenceService;
    }
    @GetMapping("/evidences")
    public String showUserEvidences(Model model, Principal principal) {
        String currentUsername = principal.getName();
        Long currentUserId = userService.getUserIdByUsername(currentUsername);
        List<Evidence> evidences = evidenceService.getEvidenceByUserId(currentUserId);
        model.addAttribute("evidences", evidences);
        return "evidences";
    }

@PostMapping("/createEvidence")
public String createEvidence(@ModelAttribute("newEvidence")Evidence evidence,Principal principal){
    String currentUsername = principal.getName();
    Long currentUserId = userService.getUserIdByUsername(currentUsername);
    evidenceService.createEvidence(evidence,currentUserId);
    return "redirect:/evidences";
}
@GetMapping("/evidences/{id}")
    public String showEvidencebyId(@PathVariable Long id, Model model){
        Evidence evidence = evidenceService.getEvidenceById(id).orElseThrow();
        model.addAttribute("evidence",evidence);
        return"evidencesMore";
    }
    @GetMapping("/evidences/create")
    public String createEvidenceForm(Model model){
        model.addAttribute("newEvidence",new Evidence());
        return "evidenceCreate";
    }

    @GetMapping("/evidences/{id}/delete")
    public String deleteEvidence(@PathVariable Long id) {
        evidenceService.deleteEvidence(id);
        return "redirect:/evidences";
    }
    @GetMapping("/evidences/{id}/edit")
    public String editEvidenceForm(@PathVariable Long id, Model model){
        Evidence evidence = evidenceService.getEvidenceById(id).orElseThrow();
        model.addAttribute("evidence",evidence);
        return"evidenceEdit";
    }
    @PostMapping("/evidences/{id}/edit")
    public String editEvidence(@PathVariable Long id,
                           @ModelAttribute("evidence") Evidence updatedEvidence,
                           RedirectAttributes redirectAttributes,Principal principal) {
        try {
            String currentUsername = principal.getName();
            Long currentUserId = userService.getUserIdByUsername(currentUsername);
            evidenceService.updateEvidence(id, updatedEvidence,currentUserId);
        } catch (EntityNotFoundException e) {

        }
        return "redirect:/evidences";
    }
}
