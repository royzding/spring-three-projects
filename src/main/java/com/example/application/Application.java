package com.example.application;

import com.vaadin.flow.component.dependency.JsModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 *  login: admin/userpass
 */
@SpringBootApplication
@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
public class Application extends SpringBootServletInitializer { //implements AppShellConfigurator {
/*
    @Override
    public void configurePage(AppShellSettings settings) {
        settings.setViewport("width=device-width, initial-scale=1");
        settings.setPageTitle("A cool vaadin app");
        settings.setBodySize("100vw", "100vh");
        settings.addMetaTag("author", "bunny");
        settings.addFavIcon("icon", "icons/icon-192.png", "192x192");
        settings.addLink("shortcut icon", "icons/favicon.ico");

        settings.addInlineFromFile(
                TargetElement.BODY,
                Position.APPEND,
                "custom.html",
                Wrapping.AUTOMATIC);
        settings.addInlineWithContents(Position.PREPEND,
                "console.log(\"foo\");", Wrapping.JAVASCRIPT);
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


