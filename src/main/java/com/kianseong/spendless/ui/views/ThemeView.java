package com.kianseong.spendless.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.Lumo;

public class ThemeView extends HorizontalLayout {

    public ThemeView() {
        Button themeToggle = new Button("Dark");
        themeToggle.addClickListener(e -> {
            String text = themeToggle.getText();
            setTheme(text.equals("Dark"));
            themeToggle.setText(text.equals("Dark") ? "Light" : "Dark");
        });

        add(themeToggle);
    }

    private void setTheme(boolean dark) {
        var js = "document.documentElement.setAttribute('theme', $0)";

        getElement().executeJs(js, dark ? Lumo.DARK : Lumo.LIGHT);
    }
}
