package com.azaan.ui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.azaan.entity.Role;
import com.azaan.entity.User;
import com.azaan.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "", autoLayout = false)
@PageTitle("Register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout implements BeforeEnterObserver{

    private final UserService userService;

@Override
public void beforeEnter(BeforeEnterEvent event) {

    Authentication authentication =
            SecurityContextHolder
            .getContext()
            .getAuthentication();


    if(authentication != null &&
       authentication.isAuthenticated() &&
       !authentication.getName().equals("anonymousUser")) {


        String role = authentication
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();


        if(role.equals("ROLE_STUDENT")) {

            event.forwardTo("student-home");

        }
        else if(role.equals("ROLE_ADMIN")) {

            event.forwardTo("admin-dashboard");

        }

    }

}

   

    public RegisterView(UserService userService) {

        this.userService = userService;

        System.out.println("===== REGISTER VIEW OPENED =====");


        H2 title = new H2("User Registration");

        TextField nameField = new TextField("Name");

        TextField usernameField = new TextField("Username");

        EmailField emailField = new EmailField("Email");

        PasswordField passwordField = new PasswordField("Password");

        ComboBox<Role> roleBox = new ComboBox<>("Role");
        roleBox.setItems(Role.values());
        roleBox.setValue(Role.STUDENT);

        Button registerButton = new Button("Register");

        Span loginText = new Span("Already have an account?");

Button loginButton = new Button("Login");

loginButton.addClickListener(event -> {

    UI.getCurrent().navigate("/login");

});

        registerButton.addClickListener(event -> {

            User user = new User();

            user.setName(nameField.getValue());
            user.setUsername(usernameField.getValue());
            user.setEmail(emailField.getValue());
            user.setPassword(passwordField.getValue());
            user.setRole(roleBox.getValue());

            try {

                userService.registerUser(user);

                Notification.show("Registration Successful");

                UI.getCurrent().navigate("login");

            } catch (Exception e) {

                e.printStackTrace();
                Notification.show("Registration Failed");

            }

        });

        setAlignItems(Alignment.CENTER);

        add(
                title,
                nameField,
                usernameField,
                emailField,
                passwordField,
                roleBox,
                registerButton,loginText,loginButton
        );
    }
}