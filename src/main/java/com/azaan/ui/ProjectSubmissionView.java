package com.azaan.ui;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;

import com.azaan.entity.Project;
import com.azaan.entity.ProjectStatus;
import com.azaan.entity.ProjectSubmission;
import com.azaan.entity.SubmissionFile;
import com.azaan.service.ProjectService;
import com.azaan.service.ProjectSubmissionService;
import com.azaan.service.SubmissionFileService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@Route("project")
@RolesAllowed("ROLE_STUDENT")
@PageTitle("Project Submission")
public class ProjectSubmissionView extends VerticalLayout
        implements HasUrlParameter<Long> {

    private final ProjectService projectService;
    private final ProjectSubmissionService projectSubmissionService;
    private final SubmissionFileService submissionFileService;

    private Project currentProject;

    private H2 title;
    private Paragraph description;
    private Paragraph deadline;

    private ComboBox<ProjectStatus> statusBox;
    private TextArea remarksArea;

    private MultiFileMemoryBuffer buffer;
    private Upload upload;

    private Button submitButton;

    public ProjectSubmissionView(
            ProjectService projectService,
            ProjectSubmissionService projectSubmissionService,
            SubmissionFileService submissionFileService) {

        this.projectService = projectService;
        this.projectSubmissionService = projectSubmissionService;
        this.submissionFileService = submissionFileService;

        setSizeFull();
        setSpacing(true);
        setPadding(true);

        title = new H2();

        description = new Paragraph();

        deadline = new Paragraph();

        statusBox = new ComboBox<>("Update Status");
        statusBox.setItems(ProjectStatus.values());
        statusBox.setWidth("250px");

        remarksArea = new TextArea("Remarks");
        remarksArea.setWidthFull();
        remarksArea.setHeight("150px");

        buffer = new MultiFileMemoryBuffer();

        upload = new Upload(buffer);
        upload.setMaxFiles(20);
        upload.setWidthFull();

        submitButton = new Button("Submit");

        add(
                title,
                description,
                deadline,
                statusBox,
                remarksArea,
                upload,
                submitButton
        );

        // Submit button logic will be added in Part 2
    }

    @Override
    public void setParameter(BeforeEvent event, Long projectId) {

        currentProject = projectService.getProjectById(projectId);

        if (currentProject == null) {

            title.setText("Project Not Found");
            return;

        }

        title.setText(currentProject.getTitle());

        description.setText(
                "Description : " +
                        currentProject.getDescription());

        deadline.setText(
                "Deadline : " +
                        currentProject.getDeadline());

        statusBox.setValue(currentProject.getStatus());

        remarksArea.setValue(
                currentProject.getRemarks() == null
                        ? ""
                        : currentProject.getRemarks()
        );

        submitButton.addClickListener(e -> {

    if (currentProject == null) {
        Notification.show("Project not found.");
        return;
    }

    // Update the project
    currentProject.setStatus(statusBox.getValue());
    currentProject.setRemarks(remarksArea.getValue());

    projectService.saveProject(currentProject);

    // Check whether a submission already exists
    Optional<ProjectSubmission> optionalSubmission =
            projectSubmissionService.getSubmission(currentProject);

    ProjectSubmission submission;

    if (optionalSubmission.isPresent()) {

        submission = optionalSubmission.get();

    } else {

        submission = new ProjectSubmission();
        submission.setProject(currentProject);
    }

    submission.setSubmittedAt(LocalDateTime.now());

    submission = projectSubmissionService.saveSubmission(submission);

    try {

        Path uploadFolder = Paths.get("uploads");

        if (!Files.exists(uploadFolder)) {
            Files.createDirectories(uploadFolder);
        }

        for (String fileName : buffer.getFiles()) {

            InputStream inputStream =
                    buffer.getInputStream(fileName);

            Path destination =
                    uploadFolder.resolve(fileName);

            Files.copy(
                    inputStream,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            SubmissionFile submissionFile =
                    new SubmissionFile();

            submissionFile.setFileName(fileName);

            submissionFile.setFilePath(destination.toString());

            submissionFile.setSubmission(submission);

            submissionFileService.saveFile(submissionFile);

            inputStream.close();
        }

        Notification.show("Project submitted successfully.");

    } catch (IOException ev) {

        Notification.show("Error while uploading files.");

        ev.printStackTrace();
    }

});
    }
}
