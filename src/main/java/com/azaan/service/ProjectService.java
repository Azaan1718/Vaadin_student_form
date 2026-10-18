package com.azaan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azaan.entity.Project;
import com.azaan.entity.User;
import com.azaan.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByStudent(User student) {

        return projectRepository.findByStudent(student);

    }

    public Project getProjectById(Long id) {

    return projectRepository.findById(id).orElse(null);

}

}