package com.example.application.config;

import com.example.application.events.MessageChangeEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.shared.Registration;
import jakarta.inject.Singleton;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

@Singleton
@Configuration
public class MsgBroadcaster {
    static Set<Consumer<MessageChangeEvent>> listeners = new LinkedHashSet<>();

    public static synchronized Registration register(
            Consumer<MessageChangeEvent> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (MsgBroadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(String message) {
        MessageChangeEvent event = new MessageChangeEvent(message);
        System.out.println("broadcast=============================" + message);
        listeners.forEach(listener -> {
            System.out.println("listener=============================" + event.getNewMessage());
            UI.getCurrent().access(() -> listener.accept(event));
        });
    }
}