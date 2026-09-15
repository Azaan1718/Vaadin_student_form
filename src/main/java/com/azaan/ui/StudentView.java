
package com.azaan.ui;
import com.azaan.service.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.azaan.entity.Student;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./styles/styles.css")



@Route("students")
@PageTitle("Student Management")
public class StudentView extends VerticalLayout {

    private final StudentService service;

    public StudentView(StudentService service) {

        this.service = service;

        setSizeFull();
        setPadding(true);
        setSpacing(true);


        H1 title = new H1("Student Data Form");
        title.addClassName("title");


        ComboBox<String> school = new ComboBox<>("School Name");
        school.setItems("Nagarjuna Model School");
        school.setValue("Nagarjuna Model School");
        school.setWidthFull();

        TextField firstName = new TextField("First Name");
        firstName.setPlaceholder("Enter first name");
        firstName.setWidthFull();

        TextField lastName = new TextField("Last Name");
        lastName.setPlaceholder("Enter last name");
        lastName.setWidthFull();

        ComboBox<String> gender = new ComboBox<>("Gender");
        gender.setItems("Male", "Female", "Other");
        gender.setWidthFull();

        TextField aspiration = new TextField("Aspiration");
        aspiration.setPlaceholder("Enter your aspiration");
        aspiration.setWidthFull();

        ComboBox<String> grade = new ComboBox<>("Class");
        grade.setItems("6", "7", "8", "9", "10");
        grade.setWidthFull();

        TextField section = new TextField("Section");
        section.setPlaceholder("Enter section");
        section.setWidthFull();

        EmailField email = new EmailField("Email ID");
        email.setPlaceholder("Enter email");
        email.setWidthFull();

        Checkbox leader = new Checkbox("Is Team Leader");

        TextArea comments = new TextArea("Comments");
        comments.setPlaceholder("Enter comments");
        comments.setWidthFull();
        comments.setHeight("120px");

        TextField guardianName = new TextField("Guardian Name");
        guardianName.setWidthFull();

        TextField guardianRelation = new TextField("Relation");
        guardianRelation.setWidthFull();

        EmailField guardianEmail = new EmailField("Guardian Email");
        guardianEmail.setWidthFull();

        Button saveButton = new Button("Save Student");
        saveButton.addClassName("save-button");


        FormLayout form = new FormLayout();
        form.addClassName("student-form");

        form.setWidthFull();

        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("800px", 2)
        );

        form.add(school, 2);

        form.add(firstName);
        form.add(lastName);

        form.add(gender);
        form.add(aspiration);

        form.add(grade);
        form.add(section);

        form.add(email);
        form.add(leader);

        form.add(comments, 2);

        form.add(guardianName, 2);
        form.add(guardianRelation, 2);
        form.add(guardianEmail, 2);


        add(title, form, saveButton);

        saveButton.addClickListener(event -> {

    Student student = new Student();

    student.setSchoolname(school.getValue());
    student.setFirstname(firstName.getValue());
    student.setLastname(lastName.getValue());
    student.setGender(gender.getValue());
    student.setAspiration(aspiration.getValue());
    student.setGrade(grade.getValue());
    student.setSection(section.getValue());
    student.setEmailid(email.getValue());
    student.setLeader(leader.getValue());
    student.setComments(comments.getValue());
    student.setGuardianname(guardianName.getValue());
    student.setGuardianrelation(guardianRelation.getValue());
    student.setGuardianemail(guardianEmail.getValue());

    service.saveStudent(student);

    Notification.show("Student Saved Successfully");

});

    }
}