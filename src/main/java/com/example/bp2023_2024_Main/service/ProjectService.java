package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.entity.Project;
import com.example.bp2023_2024_Main.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {

        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
