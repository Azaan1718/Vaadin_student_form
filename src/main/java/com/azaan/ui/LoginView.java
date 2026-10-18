package com.azaan.ui;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "login", autoLayout = false)
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public LoginView() {

        H2 title = new H2("Student Portal Login");

        LoginForm loginForm = new LoginForm();

        loginForm.setAction("login");

        setSizeFull();

        setAlignItems(Alignment.CENTER);

        setJustifyContentMode(JustifyContentMode.CENTER);

        add(title, loginForm);
    }
}