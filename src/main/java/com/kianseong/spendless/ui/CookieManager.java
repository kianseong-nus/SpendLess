package com.kianseong.spendless.ui;

import com.vaadin.flow.server.VaadinService;
import jakarta.servlet.http.Cookie;

public class CookieManager {

    public static Cookie getCookieByName(String name) {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    public static void setCookieValue(String name, String value) {
        Cookie cookie = getCookieByName(name);
        cookie.setValue(value);
        cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
        VaadinService.getCurrentResponse().addCookie(cookie);

    }

    public static String getCookieValue(String name) {
        return getCookieByName(name).getValue();
    }

}
