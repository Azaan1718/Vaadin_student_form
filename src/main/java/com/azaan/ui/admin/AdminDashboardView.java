package com.azaan.ui.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route("admin-dashboard")
@PermitAll 
public class AdminDashboardView extends VerticalLayout {

    public AdminDashboardView() {


            System.out.println("===== ADMIN DASHBOARD OPENED =====");

        H2 title = new H2("Admin Dashboard");

        Button assignProject = new Button("Assign Project");

       
        Button viewLeaderboard = new Button("View Leaderboard");

        Button searchStudent = new Button("Search Student");

        Button ProjectUpdates = new Button("ProjectUpdates");

        Button submittedProjects = new Button("Submitted Projects");


        searchStudent.addClickListener(event->{

            UI.getCurrent().navigate("search-student");
        });

        assignProject.addClickListener(e->{
            UI.getCurrent().navigate("assign");
        });

        submittedProjects.addClickListener(event -> {

    UI.getCurrent().navigate("admin/submissions");

});

        add(
            title,
            assignProject,   
            viewLeaderboard,
            searchStudent,
            submittedProjects
        );
    }
}