package com.example.application.services;

import com.example.application.config.MsgBroadcaster;
import org.springframework.stereotype.Service;

@Service
public class SharedMsgService {
    private String message = "Initial message";

    private final MsgBroadcaster broadcaster;

    public SharedMsgService(MsgBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        System.out.println("setMessage=============================");
        this.message = message;
        broadcaster.broadcast(message);
    }
}