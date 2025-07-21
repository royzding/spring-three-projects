package com.example.application.views;

import com.example.application.services.SharedMsgService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "FirstSharedMsg", layout = MainLayout.class)
@PageTitle("FirstSharedMsg | Vaadin CRM")
@SpringComponent
public class FirstSharedMsgView extends VerticalLayout  {

    public FirstSharedMsgView(SharedMsgService messageService) {
        addClassName("FirstSharedMsg-view");

        TextField input = new TextField("Enter new message:");
        Button updateBtn = new Button("Update Message");
        Span currentMessage = new Span("Current message: " + messageService.getMessage());

        updateBtn.addClickListener(e -> {
            messageService.setMessage(input.getValue());
            currentMessage.setText("Current message: " + messageService.getMessage());
        });

        add(input, updateBtn, currentMessage);
    }
}