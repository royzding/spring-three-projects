package com.example.application.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

import java.util.concurrent.Future;

public final class ViewsUtil {
    private ViewsUtil(){}

    public static Future<Void> uiAccessCommand(UI ui, Command command) {
        return ui.access(command);
    }
}
