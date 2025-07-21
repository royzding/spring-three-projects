package com.example.application.views;

import com.example.application.config.MsgBroadcaster;
import com.example.application.events.MessageChangeEvent;
import com.example.application.services.SharedMsgService;
import com.example.application.util.ViewsUtil;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "SecondSharedMsg", layout = MainLayout.class)
@PageTitle("SecondSharedMsg | Vaadin CRM")
@SpringComponent
public class SecondSharedMsgView extends VerticalLayout  {

    private Registration broadcasterReg;
    private UI ui;
    private final Span msg = new Span("Shared message: ");

    public SecondSharedMsgView(SharedMsgService messageService) {
        addClassName("SecondSharedMsg-view");

        add(msg);

        // Register for message updates
        ui = UI.getCurrent();
        broadcasterReg = MsgBroadcaster.register(this::update);

        System.out.println("SecondSharedMsgView=================");
        // Clean up when detached

        addDetachListener(e -> {
            if (broadcasterReg != null) {
                broadcasterReg.remove();
            }
        });

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        System.out.println("onAttach=================");

        // Register for message updates
        ui = attachEvent.getUI();
        broadcasterReg = MsgBroadcaster.register(this::update);

    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {

        System.out.println("onDetach=================");

        if (broadcasterReg != null) {
            broadcasterReg.remove();
            broadcasterReg = null;
        }

    }

    private void update(MessageChangeEvent value) {

        System.out.println("update=================" + value.getNewMessage());
        ViewsUtil.uiAccessCommand(ui,() -> this.msg.setText(value.getNewMessage()));

    }
}
