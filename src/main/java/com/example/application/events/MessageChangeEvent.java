package com.example.application.events;

public class MessageChangeEvent {
    private final String newMessage;

    public MessageChangeEvent(String newMessage) {
        this.newMessage = newMessage;
    }

    public String getNewMessage() {
        return newMessage;
    }
}
