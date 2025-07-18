package com.example.application.views;

import com.example.application.config.Broadcaster;
import com.example.application.util.ViewsUtil;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "broadcaster", layout = MainLayout.class)
@PageTitle("Broadcaster | Vaadin CRM")
public class BroadcasterView extends Div {
    VerticalLayout messages = new VerticalLayout();
    UI ui;
    Registration broadcasterRegistration;

    public BroadcasterView() {
        addClassName("broadcaster-view");
        TextField message = new TextField();
        Button send = new Button("Send", e -> {
            Broadcaster.broadcast(message.getValue());
            message.setValue("");
        });

        HorizontalLayout sendBar = new HorizontalLayout(message, send);

        add(sendBar, messages);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(
                //newMessage -> {ui.access(() -> messages.add(new Span(newMessage)));}
                this::update
        );
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }

    private void update(String value) {

        ViewsUtil.uiAccessCommand(ui,() -> messages.add(new Span(value)));

    }
}