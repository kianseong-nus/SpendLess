package com.kianseong.spendless.ui.views;


import com.kianseong.spendless.ui.CookieManager;
import com.kianseong.spendless.ui.appnav.AppNav;
import com.kianseong.spendless.ui.appnav.AppNavItem;
import com.kianseong.spendless.ui.views.expenses.ExpensesView;
import com.kianseong.spendless.ui.views.settings.SettingsView;
import com.kianseong.spendless.ui.views.statistics.StatisticsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.servlet.http.Cookie;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
        loadTheme();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("SpendLess");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Expenses", ExpensesView.class, LineAwesomeIcon.DOLLAR_SIGN_SOLID.create()));
        nav.addItem(new AppNavItem("Settings", SettingsView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new AppNavItem("Statistics", StatisticsView.class, LineAwesomeIcon.CHART_PIE_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        return new Footer();
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

    private void loadTheme() {
        Cookie darkModeCookie = CookieManager.getCookieByName("spendless_darkmode");
        if (darkModeCookie != null) {
            var js = "document.body.setAttribute('theme', $0)";
            getElement().executeJs(js, darkModeCookie.getValue().equals("true") ? Lumo.DARK : Lumo.LIGHT);
        } else {
            darkModeCookie = new Cookie("spendless_darkmode", "false");
            VaadinService.getCurrentResponse().addCookie(darkModeCookie);
        }
    }
}
