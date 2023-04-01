package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseDTO;
import com.kianseong.spendless.backend.expense.ExpenseService;
import com.kianseong.spendless.ui.Expense;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

public class ExpenseForm extends VerticalLayout {

    private final Dialog form = new Dialog();
    private final Button cancelButton = new Button("Cancel");
    private final Button saveButton = new Button("Add");
    private final Button deleteButton = new Button("Delete");
    private final ExpenseService expenseService;

    private final TextField descriptionField = new TextField("Description");
    private final ComboBox<String> categoryField = new ComboBox<>("Category");
    private final TextField amountField = new TextField("Amount");
    private final DatePicker dateField = new DatePicker("Date");

    public ExpenseForm(ExpenseService expenseService) {
        this.expenseService = expenseService;

        setupForm();
        add(form);
    }

    public ExpenseForm(ExpenseService expenseService,
                       String description,
                       String category,
                       float amount,
                       LocalDate date) {
        this.expenseService = expenseService;
        descriptionField.setValue(description);
        categoryField.setValue(category);
        amountField.setValue(Float.toString(amount));
        dateField.setValue(date);

    }

    private void setupForm() {
        form.setHeaderTitle("New Expense");
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        form.getFooter().add(cancelButton);
        form.getFooter().add(saveButton);
        form.add(createDialogLayout());

        cancelButton.addClickListener(e -> form.close());
    }

    private void saveExpense(ExpenseDTO expenseDTO) {
    }

    // Creates the popup form not including the header and footer
    public VerticalLayout createDialogLayout() {
        VerticalLayout layout = new VerticalLayout(descriptionField, categoryField, amountField, dateField);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");

        return layout;
    }

    public void open() {
        form.open();
    }
}
