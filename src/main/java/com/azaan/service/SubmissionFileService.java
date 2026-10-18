package com.azaan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.azaan.entity.ProjectSubmission;
import com.azaan.entity.SubmissionFile;
import com.azaan.repository.SubmissionFileRepository;

@Service
public class SubmissionFileService {

    private final SubmissionFileRepository submissionFileRepository;

    public SubmissionFileService(
            SubmissionFileRepository submissionFileRepository) {

        this.submissionFileRepository = submissionFileRepository;
    }

    public SubmissionFile saveFile(SubmissionFile file) {

        return submissionFileRepository.save(file);
    }

    public List<SubmissionFile> getFiles(ProjectSubmission submission) {

        return submissionFileRepository.findBySubmission(submission);
    }

}