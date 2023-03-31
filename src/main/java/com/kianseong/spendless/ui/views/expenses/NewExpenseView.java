package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.ui.ExpenseCategory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class NewExpenseView extends VerticalLayout {

    private final Dialog dialog;
    private final Button saveButton = new Button("Add");
    private final Button cancelButton = new Button("Cancel");

    public NewExpenseView() {
        dialog = new Dialog();
        dialog.setHeaderTitle("New Expense");
        dialog.setCloseOnOutsideClick(false);
        dialog.setCloseOnEsc(true); // Ctrl+Esc
        // TODO: Add save button listener
        cancelButton.addClickListener(e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);
        dialog.add(createDialogLayout());

        add(dialog);
    }

    public VerticalLayout createDialogLayout() {
        TextField description = new TextField("Description");
        ComboBox<ExpenseCategory> categories = new ComboBox<>("Category");
        TextField amount = new TextField("Amount");
        DatePicker date = new DatePicker("Select date");

        VerticalLayout layout = new VerticalLayout(description, categories, amount, date);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");

        return layout;
    }

    public void open() {
        dialog.open();
    }
}
