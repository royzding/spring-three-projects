package com.example.application.views;

import com.example.application.config.Broadcaster;
import com.example.application.model.Card;
import com.example.application.services.DataService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoIcon;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PermitAll
@Route(value = "components", layout = MainLayout.class)
@PageTitle("Components | Vaadin CRM")
public class ComponentsView extends Div {
    VerticalLayout messages = new VerticalLayout();
    Registration broadcasterRegistration;
    Span textDisplayBoard = new Span();

    public ComponentsView() {
        addClassName("components-view");

        TextField message = new TextField();
        Button send = new Button("Send", e -> {
            Broadcaster.broadcast(message.getValue());
            message.setValue("");
        });

        HorizontalLayout sendBar = new HorizontalLayout(send, message, new NativeLabel("Display:"), textDisplayBoard);

        add(sendBar);

        add(getHorizonlTitle("***** Radio Button, RadioGroup Examples *****"));;

        RadioButtonGroup<String> stringRadioGroup = new RadioButtonGroup<>();
        //stringRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        stringRadioGroup.setLabel("Travel class");
        stringRadioGroup.setItems("Economy", "Business", "First Class");
        stringRadioGroup.addValueChangeListener(v->this.textDisplayBoard.setText(v.getValue()));

        RadioButtonGroup<Card> cardRadioGroup = new RadioButtonGroup<>();
        cardRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        cardRadioGroup.setLabel("Payment method");

        List<Card> cards = DataService.getCards();
        cardRadioGroup.setItems(cards);
        cardRadioGroup.setValue(cards.getFirst());
        cardRadioGroup.setRenderer(new ComponentRenderer<>(card -> {

            Icon lumoIcon = LumoIcon.PHOTO.create();
            if(card.getName().equalsIgnoreCase("Visa")) {
                lumoIcon = LumoIcon.CLOCK.create();
            } else if (card.getName().equalsIgnoreCase("Master")){
                lumoIcon = LumoIcon.PHONE.create();
            }

            Span name = new Span(card.getName());
            return new Div(new HorizontalLayout(lumoIcon, name),
                    new Div("Account#:" + card.getAccountNumber()));
        }));

        cardRadioGroup.addValueChangeListener(v->this.textDisplayBoard.setText(v.getValue().getName()));

        add(new HorizontalLayout(stringRadioGroup,cardRadioGroup));

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

    protected HorizontalLayout getHorizonlTitle(String title) {

        HorizontalLayout horizontalLayout = new HorizontalLayout(new Span(title));
        horizontalLayout.setPadding(true);

        return horizontalLayout;
    }

}