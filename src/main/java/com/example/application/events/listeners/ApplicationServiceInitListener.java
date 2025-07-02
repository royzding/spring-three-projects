package com.example.application.events.listeners;

import com.vaadin.flow.component.ReconnectDialogConfiguration;
import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.server.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringComponent
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

    Logger logger = LoggerFactory.getLogger(ApplicationServiceInitListener.class);

    @Override
    public void serviceInit(ServiceInitEvent event) {

        logger.info("***** ApplicationServiceInitListener Service Init *****");

        event.addIndexHtmlRequestListener(response -> {
            // IndexHtmlRequestListener to change the bootstrap page
        });

        event.addDependencyFilter((dependencies, filterContext) -> {
            // DependencyFilter to add/remove/change dependencies sent to
            // the client
            return dependencies;
        });

        event.addRequestHandler((session, request, response) -> {
            // RequestHandler to change how responses are handled
            return false;
        });

        event.getSource().addUIInitListener(uiInitEvent -> {
            LoadingIndicatorConfiguration loadingIndicatorConfiguration = uiInitEvent.getUI().getLoadingIndicatorConfiguration();
            loadingIndicatorConfiguration.setApplyDefaultTheme(false);

            ReconnectDialogConfiguration reconnectDialogConfiguration = uiInitEvent.getUI().getReconnectDialogConfiguration();
            reconnectDialogConfiguration.setDialogText("***** ReconnectDialogConfiguration *****");
        });

        event.getSource().addSessionInitListener(e->{
            e.getSession().setErrorHandler(new ErrorHandler() {
                @Override
                public void error(ErrorEvent errorEvent) {
                    logger.error(errorEvent.getThrowable().toString());
                }
            });
        });

        event.getSource().addServiceDestroyListener(e->
                new SessionDestroyListener() {
            @Override
            public void sessionDestroy(SessionDestroyEvent sessionDestroyEvent) {
                logger.info("***** sessionDestroy done *****");
            }
        });
    }

}