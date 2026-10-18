package com.azaan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azaan.entity.ProjectSubmission;
import com.azaan.entity.SubmissionFile;

public interface SubmissionFileRepository
        extends JpaRepository<SubmissionFile, Long> {

    List<SubmissionFile> findBySubmission(ProjectSubmission submission);

}