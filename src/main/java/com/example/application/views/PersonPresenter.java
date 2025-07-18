package com.example.application.views;

import com.example.application.model.Person;
import com.vaadin.flow.spring.annotation.SpringComponent;

// Presenter
@SpringComponent
public class PersonPresenter {
    private final PersonView view;

    public PersonPresenter(PersonView view) {
        this.view = view;

        // Setup listener
        this.view.addShowFullNameListener(this::onShowFullNameClicked);
    }

    private void onShowFullNameClicked() {
        Person person = new Person(view.getFirstName(), view.getLastName());
        String fullName = person.getFirstName() + " " + person.getLastName();
        view.setFullName(fullName);
    }
}