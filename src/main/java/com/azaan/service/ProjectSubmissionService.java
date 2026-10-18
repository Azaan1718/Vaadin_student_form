package com.azaan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azaan.entity.Project;
import com.azaan.entity.ProjectSubmission;
import com.azaan.repository.ProjectSubmissionRepository;

@Service
public class ProjectSubmissionService {

    private final ProjectSubmissionRepository projectSubmissionRepository;

    public ProjectSubmissionService(
            ProjectSubmissionRepository projectSubmissionRepository) {

        this.projectSubmissionRepository = projectSubmissionRepository;
    }

    public ProjectSubmission saveSubmission(ProjectSubmission submission) {

        return projectSubmissionRepository.save(submission);
    }

    public Optional<ProjectSubmission> getSubmission(Project project) {

        return projectSubmissionRepository.findByProject(project);
    }

    public List<ProjectSubmission> getAllSubmissions() {

    return projectSubmissionRepository.findAll();

}

}