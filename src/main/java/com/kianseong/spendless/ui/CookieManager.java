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

    public static void updateCookie(String name, String value) {
        getCookieByName(name).setValue(value);
    }

}
