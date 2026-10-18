package com.azaan.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.azaan.entity.ProjectSubmission;
import com.azaan.entity.SubmissionFile;
import com.azaan.service.ProjectSubmissionService;
import com.azaan.service.SubmissionFileService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import jakarta.annotation.security.RolesAllowed;

@Route("admin/submissions")
@RolesAllowed("ROLE_ADMIN")
@PageTitle("Submitted Projects")
public class AdminSubmittedProjectsView extends VerticalLayout {

    private final ProjectSubmissionService projectSubmissionService;
    private final SubmissionFileService submissionFileService;

    private Grid<ProjectSubmission> grid;

    public AdminSubmittedProjectsView(
            ProjectSubmissionService projectSubmissionService,
            SubmissionFileService submissionFileService) {

        this.projectSubmissionService = projectSubmissionService;
        this.submissionFileService = submissionFileService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Submitted Projects");

        grid = new Grid<>(ProjectSubmission.class, false);

        grid.addColumn(submission ->
                submission.getProject()
                        .getStudent()
                        .getUsername())
                .setHeader("Student")
                .setAutoWidth(true);

        grid.addColumn(submission ->
                submission.getProject()
                        .getTitle())
                .setHeader("Project")
                .setAutoWidth(true);

        grid.addColumn(submission ->
                submission.getProject()
                        .getStatus())
                .setHeader("Status")
                .setAutoWidth(true);

        grid.addColumn(ProjectSubmission::getSubmittedAt)
                .setHeader("Submitted At")
                .setAutoWidth(true);

        grid.addComponentColumn(submission -> {

            Button viewButton = new Button("View");

            viewButton.addClickListener(event -> {

                showSubmissionDialog(submission);

            });

            return viewButton;

        }).setHeader("Action");

        List<ProjectSubmission> submissions =
                projectSubmissionService.getAllSubmissions();

        grid.setItems(submissions);

        add(title, grid);

    }

        private void showSubmissionDialog(ProjectSubmission submission) {

        Dialog dialog = new Dialog();
        dialog.setWidth("700px");

        VerticalLayout layout = new VerticalLayout();

        layout.add(new H2("Submission Details"));

        layout.add(new Paragraph(
                "Student : "
                        + submission.getProject()
                        .getStudent()
                        .getUsername()));

        layout.add(new Paragraph(
                "Project : "
                        + submission.getProject()
                        .getTitle()));

        layout.add(new Paragraph(
                "Description : "
                        + submission.getProject()
                        .getDescription()));

        layout.add(new Paragraph(
                "Deadline : "
                        + submission.getProject()
                        .getDeadline()));

        layout.add(new Paragraph(
                "Status : "
                        + submission.getProject()
                        .getStatus()));

        layout.add(new Paragraph(
                "Remarks : "
                        + submission.getProject()
                        .getRemarks()));

        layout.add(new Paragraph(
                "Points : "
                        + submission.getProject()
                        .getPoints()));

        layout.add(new Paragraph(
                "Submitted At : "
                        + submission.getSubmittedAt()));

        H2 filesTitle = new H2("Uploaded Files");

        layout.add(filesTitle);

        List<SubmissionFile> files =
                submissionFileService.getFiles(submission);

        if (files.isEmpty()) {

            layout.add(new Paragraph("No files uploaded."));

        } else {

            for (SubmissionFile file : files) {

                StreamResource resource =
                        new StreamResource(
                                file.getFileName(),
                                () -> {
                                    try {
                                        return new FileInputStream(
                                                file.getFilePath());
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                        return null;
                                    }
                                });

                Anchor download =
                        new Anchor(resource, file.getFileName());

                download.getElement()
                        .setAttribute("download", true);

                layout.add(download);
            }

        }

        Button closeButton = new Button("Close");

        closeButton.addClickListener(event -> dialog.close());

        layout.add(closeButton);

        dialog.add(layout);

        dialog.open();
    }

}