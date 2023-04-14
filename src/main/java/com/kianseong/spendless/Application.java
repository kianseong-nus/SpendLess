package com.kianseong.spendless;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of SpendLess.
 */
@SpringBootApplication
@Theme(value = "spendless")
public class Application implements AppShellConfigurator {

    /**
     * Main method to jumpstart the program.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
