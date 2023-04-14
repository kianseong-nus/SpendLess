package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseService;
import com.kianseong.spendless.backend.expense.Expense;
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

    /**
     * Expense ID of existing expense. Set to -1 when adding a new expense.
     */
    private final int expenseId;
    /**
     * Popup container.
     */
    private Dialog form;
    /**
     * Inject ExpenseService.
     */
    private final ExpenseService expenseService;

    /**
     * Description.
     */
    private final TextField descriptionField = new TextField("Description");
    /**
     * Category.
     */
    private final TextField categoryField = new TextField("Category");
    /**
     * Indicates whether transaction is an income.
     */
    private final Checkbox isIncomeCheckbox = new Checkbox("Income");
    /**
     * Amount.
     */
    private final TextField amountField = new TextField("Amount");
    /**
     * Date.
     */
    private final DatePicker dateField = new DatePicker("Date");

    /**
     * Constructor for new expense.
     * @param expenseService to be injected
     */
    public ExpenseForm(final ExpenseService expenseService) {
        this.expenseService = expenseService;
        expenseId = -1;
        setupNewForm();
    }

    /**
     * Constructor for existing expense.
     * @param expenseService to be injected
     * @param expense expense to load into form
     */
    public ExpenseForm(final ExpenseService expenseService,
                       final Expense expense) {
        this.expenseService = expenseService;
        expenseId = expense.getId();
        setupExistingForm(expense);
    }

    private void setupNewForm() {
        dateField.setValue(LocalDate.now());
        form = createForm("New Expense");
        form.getFooter().add(createCancelButton(), createSaveButton());
        form.add(createDialogLayout());
    }

    private void setupExistingForm(final Expense expense) {
        descriptionField.setValue(expense.getDescription());
        categoryField.setValue(expense.getCategory());
        isIncomeCheckbox.setValue(expense.getIsIncome());
        amountField.setValue(Float.toString(expense.getAmount()));
        dateField.setValue(expense.getDate());

        Button deleteButton = new Button("Delete");
        deleteButton.addClickListener(e -> deleteExpense(expense));

        form = createForm("Expense");
        form.getFooter().add(
                createCancelButton(),
                deleteButton,
                createSaveButton()
        );
        form.add(createDialogLayout());
    }

    /**
     * Opens the form.
     */
    public void open() {
        form.open();
    }

    private Dialog createForm(final String title) {
        form = new Dialog();
        form.setHeaderTitle(title);
        form.getHeader().add(isIncomeCheckbox);
        form.setCloseOnOutsideClick(false);
        form.setCloseOnEsc(true); // Ctrl+Esc
        return form;
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

    /**
     * Creates the popup form not including the header and footer.
     * @return VerticalLayout popup form component
     */
    private VerticalLayout createDialogLayout() {
        VerticalLayout layout = new VerticalLayout(
                descriptionField,
                categoryField,
                amountField,
                dateField
        );
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

    private void saveExpense(final Expense expense) {
        expenseService.saveExpense(expense);
        form.close();
        String message = expenseId != -1 ? "Expense edited!" : "Expense added!";
        Notification
                .show(message)
                .setPosition(Notification.Position.TOP_CENTER);
    }

    private void deleteExpense(final Expense expense) {
        form.close();
        expenseService.deleteExpense(expense);
        Notification
                .show("Expense deleted!")
                .setPosition(Notification.Position.TOP_CENTER);
    }
}
