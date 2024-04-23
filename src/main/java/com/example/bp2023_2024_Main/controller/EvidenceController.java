package com.example.bp2023_2024_Main.controller;
import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.service.EvidenceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller
public class EvidenceController {
  private final EvidenceService evidenceService;
    public EvidenceController(EvidenceService evidenceService, HttpServletRequest httpServletRequest) {
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
    @GetMapping("/evidences/create")
    public String createEvidenceForm(Model model){
        model.addAttribute("newEvidence",new Evidence());
        return "evidenceCreate";
    }
    @PostMapping("/createEvidence")
    public String createEvidence(@ModelAttribute("newEvidence")Evidence evidence){
        evidenceService.createEvidence(evidence);
        return "redirect:/evidences";
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
                           RedirectAttributes redirectAttributes) {
        try {
            evidenceService.updateEvidence(id, updatedEvidence);
        } catch (EntityNotFoundException e) {

        }
        return "redirect:/evidences";
    }
}
//@GetMapping("/me")
//    public String getMyEvidences(Model model) {
//
//        Long userId = 1L; // Assuming "loggedUserId" holds user ID in session
//
//        if (userId != null) {
//            List<Evidence> evidences = evidenceService.getMyEvidences(userId);
//            model.addAttribute("evidences", evidences);
//            return "evidences"; // Name of your view template
//        } else {
//            // Handle unauthorized access (e.g., redirect to login page)
//            return "redirect:/login"; // Redirect to login page if not logged in
//        }
//    }
