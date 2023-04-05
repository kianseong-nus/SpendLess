package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseService;
import com.kianseong.spendless.ui.Expense;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

public class ExpenseForm extends VerticalLayout {

    private int expenseId;

    private final Dialog form = new Dialog();
    private final Button deleteButton = new Button("Delete");
    private final ExpenseService expenseService;

    private final TextField descriptionField = new TextField("Description");
    private final TextField categoryField = new TextField("Category");
    private final Checkbox isIncomeCheckbox = new Checkbox("Income");
    private final TextField amountField = new TextField("Amount");
    private final DatePicker dateField = new DatePicker("Date");

    public ExpenseForm(ExpenseService expenseService) {
        this.expenseService = expenseService;
        expenseId = -1;
        setupNewForm();
    }

    public ExpenseForm(ExpenseService expenseService, Expense expense) {
        this.expenseService = expenseService;
        expenseId = expense.getId();
        setupExistingForm(expense);
    }

    private void setupNewForm() {
        dateField.setValue(LocalDate.now());
        form.setHeaderTitle("New expense");
        form.getHeader().add(isIncomeCheckbox);
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        form.getFooter().add(createCancelButton(), createSaveButton());
        form.add(createDialogLayout());
    }

    private void setupExistingForm(Expense expense) {
        descriptionField.setValue(expense.getDescription());
        categoryField.setValue(expense.getCategory());
        isIncomeCheckbox.setValue(expense.getIsIncome());
        amountField.setValue(Float.toString(expense.getAmount()));
        dateField.setValue(expense.getDate());

        form.setHeaderTitle("Expense");
        form.getHeader().add(isIncomeCheckbox);
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        form.getFooter().add(createCancelButton(), deleteButton, createSaveButton());
        form.add(createDialogLayout());

        deleteButton.addClickListener(e -> deleteExpense(expense));
    }

    public void open() {
        form.open();
    }

    private Button createCancelButton() {
        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> form.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return cancel;
    }

    private Button createSaveButton() {
        Button save = new Button("Save");
        save.addClickListener(e -> {
            if (expenseId != -1) {
                saveExpense(new Expense(
                        expenseId,
                        descriptionField.getValue(),
                        categoryField.getValue(),
                        isIncomeCheckbox.getValue(),
                        Float.parseFloat(amountField.getValue()),
                        dateField.getValue()
                ));
            } else {
                saveExpense(collect());
            }
        });
        return save;
    }

    // Creates the popup form not including the header and footer
    private VerticalLayout createDialogLayout() {
        VerticalLayout layout = new VerticalLayout(descriptionField, categoryField, amountField, dateField);
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setAlignItems(Alignment.STRETCH);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");

        return layout;
    }

    private Expense collect() {
        return new Expense(
                descriptionField.getValue(),
                categoryField.getValue(),
                isIncomeCheckbox.getValue(),
                Float.parseFloat(amountField.getValue()),
                dateField.getValue()
        );
    }

    private void saveExpense(Expense expense) {
        expenseService.saveExpense(expense);
        form.close();
        String message = expenseId != -1 ? "Expense edited!" : "Expense added!";
        Notification.show(message).setPosition(Notification.Position.TOP_CENTER);
    }

    private void deleteExpense(Expense expense) {
        form.close();
        expenseService.deleteExpense(expense);
        Notification.show("Expense deleted!").setPosition(Notification.Position.TOP_CENTER);
    }
}
