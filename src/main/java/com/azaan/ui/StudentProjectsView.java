package com.azaan.ui;


import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.azaan.entity.Project;
import com.azaan.entity.User;
import com.azaan.service.ProjectService;
import com.azaan.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;


@Route("myprojects")
@RolesAllowed("ROLE_STUDENT")
@PageTitle("My Projects")
public class StudentProjectsView extends VerticalLayout {


    private final ProjectService projectService;
    private final UserService userService;



    public StudentProjectsView(ProjectService projectService,
                               UserService userService) {


        this.projectService = projectService;
        this.userService = userService;



        H2 title = new H2("My Projects");

        Grid<Project> grid = new Grid<>(Project.class,false);

        grid.addColumn(Project::getTitle)
    .setHeader("Title");

grid.addColumn(Project::getDescription)
    .setHeader("Description");

grid.addColumn(Project::getDeadline)
    .setHeader("Deadline");

grid.addColumn(Project::getStatus)
    .setHeader("Status");

grid.addColumn(Project::getPoints)
    .setHeader("Points");

    grid.addComponentColumn(project -> {

    Button openButton = new Button("Open");

    openButton.addClickListener(event -> {

        getUI().ifPresent(ui ->
                ui.navigate("project/" + project.getId()));

    });

    return openButton;

}).setHeader("Action");

        Authentication authentication =
                SecurityContextHolder
                .getContext()
                .getAuthentication();



        String username =
                authentication.getName();



        User student =
                userService.findByUsername(username);



        List<Project> projects =
                projectService.getProjectsByStudent(student);

        



        grid.setItems(projects);



        add(
            title,
            grid
        );

    }

}