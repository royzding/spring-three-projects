package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "routewithparameters", layout = MainLayout.class)
@PageTitle("RouteWithParameters | Vaadin CRM")
public class RouteWithParametersView extends Div
        implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event,
                             @WildcardParameter String parameter) {
        if (parameter.isEmpty()) {
            setText("Welcome anonymous.");
        } else {
            setText(String.format("Handling parameter %s.", parameter));
        }
    }

/*

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        setText(String.format("Hello, %s!", parameter));
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        if (parameter == null) {
            setText("Welcome anonymous.");
        } else {
            setText(String.format("Welcome %s.", parameter));
        }
    }


 */
}