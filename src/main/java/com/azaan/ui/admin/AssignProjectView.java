package com.azaan.ui.admin;

import java.time.LocalDate;

import com.azaan.entity.Project;
import com.azaan.entity.ProjectStatus;
import com.azaan.entity.User;
import com.azaan.service.ProjectService;
import com.azaan.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;


@Route("assign")
@PageTitle("Assign Projects")
@RolesAllowed("ROLE_ADMIN")
public class AssignProjectView extends VerticalLayout {


    private final ProjectService projectService;
    private final UserService userService;


    public AssignProjectView(ProjectService projectService,
                             UserService userService) {


        this.projectService = projectService;
        this.userService = userService;


        H2 title = new H2("Assign Project");


        TextField projectTitle =
                new TextField("Project Title");


        TextArea description =
                new TextArea("Description");


        DatePicker deadline =
                new DatePicker("Deadline");


        // Prevent selecting past dates
        deadline.setMin(LocalDate.now());


        IntegerField points =
                new IntegerField("Points");


        TextArea remarks =
                new TextArea("Remarks");



        ComboBox<User> student =
                new ComboBox<>("Select Student");


        /*
         * Only students should appear.
         */
        student.setItems(userService.getAllUsers());


        /*
         * User object is stored internally,
         * but username is displayed.
         */
        student.setItemLabelGenerator(User::getUsername);



        Button assign =
                new Button("Assign Project");



        assign.addClickListener(event -> {


            Project project = new Project();



            project.setTitle(
                    projectTitle.getValue()
            );


            project.setDescription(
                    description.getValue()
            );


            project.setDeadLine(
                    deadline.getValue()
            );


            project.setPoints(
                    points.getValue()
            );


            project.setRemarks(
                    remarks.getValue()
            );


            project.setStudent(
                    student.getValue()
            );



            /*
             * Every newly assigned project
             * starts with PENDING status.
             */
            project.setStatus(
                    ProjectStatus.PENDING
            );



            projectService.saveProject(project);



            Notification.show(
                    "Project Assigned Successfully"
            );



            // Clear form after assigning

            projectTitle.clear();

            description.clear();

            deadline.clear();

            points.clear();

            remarks.clear();

            student.clear();


        });



        add(
            title,
            projectTitle,
            description,
            student,
            deadline,
            points,
            remarks,
            assign
        );

    }

}