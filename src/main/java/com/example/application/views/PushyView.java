package com.example.application.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "pushy", layout = MainLayout.class)
@PageTitle("Pushy | Vaadin CRM")
public class PushyView extends VerticalLayout {
    private FeederThread thread;
    private final Span span = new Span("Waiting for updates");

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        add(span);

        // Start the data feed thread
        thread = new FeederThread(attachEvent.getUI(), this, span);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }

    private static class FeederThread extends Thread {
        private final UI ui;
        private final PushyView view;
        private final Span span;

        private int count = 0;

        public FeederThread(UI ui, PushyView view, Span span) {
            this.ui = ui;
            this.view = view;
            this.span = span;
        }

        @Override
        public void run() {
            try {

                // Update the data for a while
                while (count < 10) {

                    // Sleep to emulate background work
                    Thread.sleep(500);
                    String message = "This is update " + count++;

                    ui.access(() -> {
                        span.setText(message);
                        view.add(new Span(message));
                    });
                }

                // Inform that we're done
                ui.access(() -> {
                    span.setText("Done updating");
                    view.add(new Span("Done updating"));
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}