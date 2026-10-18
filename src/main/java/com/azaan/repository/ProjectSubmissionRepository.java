package com.azaan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azaan.entity.Project;
import com.azaan.entity.ProjectSubmission;

public interface ProjectSubmissionRepository
        extends JpaRepository<ProjectSubmission, Long> {

    Optional<ProjectSubmission> findByProject(Project project);

}