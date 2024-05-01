package com.example.bp2023_2024_Main.service.impl;

import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.repository.EvidenceRepository;
import com.example.bp2023_2024_Main.repository.UserRepository;
import com.example.bp2023_2024_Main.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EvidenceServiceImpl implements EvidenceService {

@Autowired
    private final EvidenceRepository evidenceRepository;
@Autowired
private final UserRepository userRepository;

    public EvidenceServiceImpl(EvidenceRepository evidenceRepository, UserRepository userRepository) {
        this.evidenceRepository = evidenceRepository;
        this.userRepository = userRepository;
    }
    public List<Evidence> getEvidenceByUserId(Long userId) {
        return evidenceRepository.findByUserId(userId);
    }


    @Override
    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }

    @Override
    public Optional<Evidence> getEvidenceById(Long id) {
        Optional<Evidence> optionalEvidence = evidenceRepository.findById(id);
        return optionalEvidence;
    }

    @Override
    public Evidence createEvidence(Evidence evidence, Long userId) {

        evidence.setUser(userRepository.getById(userId));
        return evidenceRepository.save(evidence);

    }

    @Override
    public void deleteEvidence(Long id) {
        evidenceRepository.deleteById(id);
    }

    @Override
    public Evidence updateEvidence(Long id, Evidence updatedEvidence,Long userId) {
        Evidence existingEvidence = new Evidence();
        existingEvidence = evidenceRepository.findById(id).orElseThrow();
        // Update the properties of the existing user
        existingEvidence.setCharge(updatedEvidence.getCharge());
        existingEvidence.setDepartment(updatedEvidence.getDepartment());
        existingEvidence.setEstTime(updatedEvidence.getEstTime());
        existingEvidence.setMonth(updatedEvidence.getMonth());
        existingEvidence.setOrderNum(updatedEvidence.getOrderNum());
        existingEvidence.setProject(updatedEvidence.getProject());
        existingEvidence.setState(updatedEvidence.getState());
        existingEvidence.setTaskName(updatedEvidence.getTaskName());
        existingEvidence.setTimeSpent(updatedEvidence.getTimeSpent());
        existingEvidence.setTotal(updatedEvidence.getTotal());
        existingEvidence.setUser(userRepository.getById(userId));

        return evidenceRepository.save(existingEvidence);
    }
}
