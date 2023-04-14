package com.kianseong.spendless.ui.views.settings;

import com.kianseong.spendless.ui.CookieManager;
import com.kianseong.spendless.ui.Theme;
import com.kianseong.spendless.ui.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.kianseong.spendless.ui.Theme.darkMode;

@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
public class SettingsView extends VerticalLayout {

    protected Button button = new Button(darkMode ? "Light" : "Dark");

    public SettingsView() {
        setSizeFull();
        add(createLightDarkModeToggle());
    }

    private HorizontalLayout createLightDarkModeToggle() {
        Text des = new Text("Toggle light or dark mode. Click the button to switch to the mode stated on the button.");
        button.addClickListener(e -> {
            Theme.setTheme(getElement(), !darkMode);
            button.setText(darkMode ? "Light" : "Dark");
            Notification.show("Updated");
        });

        return new HorizontalLayout(des, button);
    }

}
