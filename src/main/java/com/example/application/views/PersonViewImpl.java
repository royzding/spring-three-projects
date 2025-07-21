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

import java.util.function.Supplier;

@PermitAll
@Route(value = "MVPPersonView", layout = MainLayout.class)
@PageTitle("MVPPersonView | Vaadin CRM")
@SpringComponent
public class PersonViewImpl extends VerticalLayout implements PersonView {

    private final TextField firstNameField = new TextField("First Name");
    private final TextField lastNameField = new TextField("Last Name");
    private final Span fullNameLabel = new Span();
    private Button showFullNameButton = new Button("Show Full Name from Runnable");

    private Runnable showFullNameListener;
    private Supplier<String> showFullNameSupplier;

    public PersonViewImpl() {

        showFullNameButton.addClickListener(e -> {
            if (showFullNameListener != null) showFullNameListener.run();
        });

        Button showSupplierButton = new Button("Show Full Name from Supplier");
        showSupplierButton.addClickListener(e -> {
            if (showFullNameSupplier != null) showFullNameSupplier.get();
        });

        add(firstNameField, lastNameField, showFullNameButton, showSupplierButton, fullNameLabel);
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

    @Override
    public void addShowFullNameSupplier(Supplier<String> supplier) {
        this.showFullNameSupplier = supplier;
    }
}