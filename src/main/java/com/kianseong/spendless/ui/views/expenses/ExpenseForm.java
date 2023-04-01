package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseDTO;
import com.kianseong.spendless.backend.expense.ExpenseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ExpenseForm extends VerticalLayout {

    private final Dialog form = new Dialog();
    private final Button cancelButton = new Button("Cancel");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final ExpenseService expenseService;

    private final TextField descriptionField = new TextField("Description");
    private final TextField categoryField = new TextField("Category");
    private final TextField amountField = new TextField("Amount");
    private final DatePicker dateField = new DatePicker("Date");

    public ExpenseForm(ExpenseService expenseService) {
        this.expenseService = expenseService;
        setupNewForm();
    }

    public ExpenseForm(ExpenseService expenseService, ExpenseDTO expenseDTO) {
        this.expenseService = expenseService;
        setupExistingForm(expenseDTO);
    }

    private void setupNewForm() {
        form.setHeaderTitle("New expense");
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        form.getFooter().add(cancelButton);
        form.getFooter().add(saveButton);
        form.add(createDialogLayout());

        cancelButton.addClickListener(e -> form.close());
        saveButton.addClickListener(e -> saveExpense(collect()));
    }

    private void setupExistingForm(ExpenseDTO expenseDTO) {
        descriptionField.setValue(expenseDTO.description());
        categoryField.setValue(expenseDTO.category());
        amountField.setValue(Float.toString(expenseDTO.amount()));
        dateField.setValue(expenseDTO.date());

        form.setHeaderTitle("Expense");
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        form.getFooter().add(cancelButton);
        form.getFooter().add(deleteButton);
        // TODO: Add cancel button to cancel all changes
        form.getFooter().add(saveButton);
        form.add(createDialogLayout());

        cancelButton.addClickListener(e -> form.close());
    }

    private void saveExpense(ExpenseDTO expenseDTO) {
        expenseService.saveExpense(expenseDTO);
        form.close();
        Notification.show("Expense added!").setPosition(Notification.Position.TOP_CENTER);
    }

    private void deleteExpense(ExpenseDTO expenseDTO) {

    }

    // Creates the popup form not including the header and footer
    private VerticalLayout createDialogLayout() {
        VerticalLayout layout = new VerticalLayout(descriptionField, categoryField, amountField, dateField);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");

        return layout;
    }

    private ExpenseDTO collect() {
        return new ExpenseDTO(
                descriptionField.getValue(),
                categoryField.getValue(),
                Float.parseFloat(amountField.getValue()),
                dateField.getValue()
        );
    }

    public void open() {
        form.open();
    }
}
