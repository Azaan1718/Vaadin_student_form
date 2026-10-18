package com.azaan.ui.admin;

import java.util.List;

import com.azaan.entity.Project;
import com.azaan.entity.User;
import com.azaan.service.ProjectService;
import com.azaan.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

@Route("search-student")
@RolesAllowed("ROLE_ADMIN")
public class SearchStudentView extends VerticalLayout {

    private final UserService userService;
    private final ProjectService projectService;

    private VerticalLayout studentResults;
    private Grid<Project> projectGrid;

    public SearchStudentView(UserService userService,
                             ProjectService projectService) {

        this.userService = userService;
        this.projectService = projectService;


        H2 title = new H2("Search Student");


        TextField searchField = new TextField();

        searchField.setPlaceholder("Search student username");
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        studentResults = new VerticalLayout();


        projectGrid = new Grid<>(Project.class);


        projectGrid.setColumns(
                "title",
                "status",
                "points"
        );


        searchField.addValueChangeListener(event -> {

            String text = event.getValue();

            studentResults.removeAll();


            if(text.length() > 0) {

                List<User> students =
                        userService.searchStudents(text);


                for(User student : students) {


                    Button studentButton =
                            new Button(student.getUsername());


                    studentButton.addClickListener(e -> {

                        showStudentHistory(student);

                    });


                    studentResults.add(studentButton);
                }
            }

        });


        add(
            title,
            searchField,
            studentResults,
            projectGrid
        );
    }



    private void showStudentHistory(User student) {


        List<Project> projects =
                projectService.getProjectsByStudent(student);


        projectGrid.setItems(projects);

    }

}