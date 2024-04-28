package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.entity.Evidence;

import java.util.List;
import java.util.Optional;

public interface EvidenceService {
    List<Evidence> getEvidenceByUserId(Long userId);
    List<Evidence> getAllEvidence();
    Optional<Evidence> getEvidenceById(Long id);
    Evidence createEvidence(Evidence evidence, Long userId);
    void deleteEvidence(Long id);
    Evidence updateEvidence(Long id,Evidence updatedEvidence,Long userId);

}
