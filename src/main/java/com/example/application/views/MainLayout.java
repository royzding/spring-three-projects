package com.example.application.views;

import com.example.application.security.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoIcon;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new Icon(VaadinIcon.HOME),
          new DrawerToggle(),
                logo,
                logout
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header); 

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        addToDrawer(new VerticalLayout(
                //listLink,
                createMenuLink(ListView.class, "List", new Icon(VaadinIcon.LIST)),
                //new RouterLink("Components", ComponentsView.class),
                createMenuLink(ComponentsView.class, "Components", LumoIcon.CLOCK.create()),
                //new RouterLink("Dashboard", DashboardView.class),
                createMenuLink(DashboardView.class, "Dashboard", new Icon(VaadinIcon.DASHBOARD)),
                //new RouterLink("Pushy", PushyView.class),
                createMenuLink(PushyView.class, "Pushy", new Icon(VaadinIcon.CLOCK)),
                new RouterLink("RouteWithParametersView", RouteWithParametersView.class),
                new RouterLink("Broadcaster", BroadcasterView.class),
                new RouterLink("UIEventBus", UIEventBusView.class),
                new RouterLink("MVPPersonView", PersonViewImpl.class),
                new RouterLink("About", AboutView.class)
        ));
    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass, String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink(viewClass); // Creates RouterLink for the view
        routerLink.setClassName("menu-link"); // Optional: Add a CSS class for styling
        routerLink.add(icon); // Adds the icon to the RouterLink
        routerLink.add(new Span("  " + caption)); // Adds the caption as a Span element
        icon.setSize("18px"); // Adjust icon size as needed
        return routerLink;
    }

}