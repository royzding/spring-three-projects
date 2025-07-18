package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "MVPPersonView", layout = MainLayout.class)
@PageTitle("MVPPersonView | Vaadin CRM")
@SpringComponent
public class PersonViewImpl extends VerticalLayout implements PersonView {

    private TextField firstNameField = new TextField("First Name");
    private TextField lastNameField = new TextField("Last Name");
    private Span fullNameLabel = new Span();
    private Button showFullNameButton = new Button("Show Full Name");

    private Runnable showFullNameListener;

    public PersonViewImpl() {
        add(firstNameField, lastNameField, showFullNameButton, fullNameLabel);
        showFullNameButton.addClickListener(e -> {
            if (showFullNameListener != null) showFullNameListener.run();
        });
    }

    @PostConstruct
    public void init() {
        new PersonPresenter(this);
    }

    @Override
    public void setFullName(String fullName) {
        fullNameLabel.setText("Full Name: " + fullName);
    }

    @Override
    public String getFirstName() {
        return firstNameField.getValue();
    }

    @Override
    public String getLastName() {
        return lastNameField.getValue();
    }

    @Override
    public void addShowFullNameListener(Runnable listener) {
        this.showFullNameListener = listener;
    }
}