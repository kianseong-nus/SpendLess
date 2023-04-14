package com.kianseong.spendless.ui;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.theme.lumo.Lumo;

public class Theme {

    private static final String DARK_MODE = "spendless_darkmode";
    public static boolean darkMode = Boolean.parseBoolean(CookieManager.getCookieValue(Theme.DARK_MODE));

    public static void setTheme(final Element element, final boolean isDark) {
        darkMode = isDark;
        var js = "document.body.setAttribute('theme', $0)";
        element.executeJs(js, isDark ? Lumo.DARK : Lumo.LIGHT);
        CookieManager.setCookieValue(DARK_MODE, Boolean.toString(darkMode));
    }
}
