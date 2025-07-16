package com.example.application.views;

import com.example.application.config.Broadcaster;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.boot.info.BuildProperties;

@PermitAll
@Route(value = "About", layout = MainLayout.class)
@PageTitle("About | Vaadin CRM")
@SpringComponent
public class AboutView extends Div {
    VerticalLayout verticalLayout = new VerticalLayout();
    Registration broadcasterRegistration;
    Span textDisplayBoard = new Span();

    private final BuildProperties buildProperties;

    public AboutView(BuildProperties buildProperties) {
        addClassName("About-view");

        this.buildProperties = buildProperties;

        textDisplayBoard.setText(this.buildProperties.getVersion());

        //add(textDisplayBoard);

        verticalLayout.add(textDisplayBoard,
                new Span(this.buildProperties.getName()),
                new Span(this.buildProperties.get("custom.value")),
                new Span(this.buildProperties.get("java.version")),
                new Span(this.buildProperties.get("description"))
                );
        add(verticalLayout);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(newMessage -> {
            ui.access(() -> textDisplayBoard.setText(newMessage));
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }


}