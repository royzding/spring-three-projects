package com.example.application.config;

import com.vaadin.flow.component.page.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;

@Theme(value = "flowcrmtutorial")
@PWA(
        name = "Vaadin CRM",
        shortName = "CRM",
        offlinePath="offline.html",
        offlineResources = { "./images/offline.png"}
)
@Push(transport = Transport.WEBSOCKET_XHR)
@PageTitle("CRM Application")
@Viewport("width=device-width, initial-scale=1")
@BodySize(height = "100vh", width = "100vw")
public class ApplicationShellConfigurator implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {

        settings.addMetaTag("author", "bunny");
/*
        settings.addFavIcon("icon", "icons/icon-192.png", "192x192");
        settings.addLink("shortcut icon", "icons/favicon.ico");

        settings.addInlineFromFile(
                TargetElement.BODY,
                Inline.Position.APPEND,
                "custom.html",
                Inline.Wrapping.AUTOMATIC);
        settings.addInlineWithContents(Inline.Position.PREPEND,
                "console.log(\"foo\");", Inline.Wrapping.JAVASCRIPT);

 */
    }

}