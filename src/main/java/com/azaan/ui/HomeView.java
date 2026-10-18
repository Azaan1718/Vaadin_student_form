
package com.azaan.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;

import jakarta.annotation.security.PermitAll;

@Route("student-home")
@PageTitle("Home")
@PermitAll 
public class HomeView extends VerticalLayout {

    public HomeView() {

        System.out.println("===== HOME VIEW CREATED =====");

        H1 title = new H1("Student Project Portal");

        H3 welcome = new H3("Welcome!");

        Paragraph profile = new Paragraph("This is your dashboard.");

        Button projects = new Button("My Projects");

        Button leaderboard = new Button("Leaderboard");

        Button logout = new Button("Logout");

        projects.addClickListener(e->{
            UI.getCurrent().navigate("myprojects");
        });

        add(
                title,
                welcome,
                profile,
                projects,
                leaderboard,
                logout
        );
    }
}

