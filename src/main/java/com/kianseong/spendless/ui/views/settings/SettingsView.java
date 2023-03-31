package com.kianseong.spendless.ui.views.settings;

import com.kianseong.spendless.ui.CookieManager;
import com.kianseong.spendless.ui.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.lumo.Lumo;
import jakarta.servlet.http.Cookie;

@PageTitle("Settings")
@Route(value = "/settings", layout = MainLayout.class)
public class SettingsView extends VerticalLayout {

    private static final String DARK_MODE_COOKIE = "DarkMode";
    private static Cookie darkModeCookie = CookieManager.getCookieByName(DARK_MODE_COOKIE);

    public SettingsView() {
        setSizeFull();
        add(createLightDarkModeToggle());
    }

    private HorizontalLayout createLightDarkModeToggle() {
        Text des = new Text("Toggle light or dark mode. Click the button to switch to the mode stated on the button.");
        Button button = new Button();
        if (darkModeCookie != null) {
            button.setText(darkModeCookie.getValue().equals("Dark") ? "Light" : "Dark");
            var js = "document.documentElement.setAttribute('theme', $0)";
            getElement().executeJs(js, button.getText().equals("Dark") ? Lumo.LIGHT : Lumo.DARK);
        } else {
            button.setText("Dark");
            darkModeCookie = new Cookie(DARK_MODE_COOKIE, "Dark");
            VaadinService.getCurrentResponse().addCookie(darkModeCookie);
        }
        button.addClickListener(e -> {
            String text = button.getText();
            var js = "document.documentElement.setAttribute('theme', $0)";
            getElement().executeJs(js, text.equals("Dark") ? Lumo.DARK : Lumo.LIGHT);
            CookieManager.updateCookie(DARK_MODE_COOKIE, text.equals("Dark") ? "Light" : "Dark");
            button.setText(text.equals("Dark") ? "Light" : "Dark");
        });
        darkModeCookie.setPath(VaadinService.getCurrentRequest().getContextPath());

        return new HorizontalLayout(des, button);
    }

}
