package com.kianseong.spendless.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class ExpenseForm extends FormLayout {
    TextField description = new TextField("Description");
    ComboBox<ExpenseCategory> categories = new ComboBox<>("Category");
    TextField amount = new TextField("Amount");

    Binder<Expense> binder = new BeanValidationBinder<>(Expense.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    public ExpenseForm() {
        binder.bindInstanceFields(this);

        add(description, categories, amount, createButtonsLayout());
    }

    public void setExpense(Expense expense) {
        binder.setBean(expense); // bind values of expense to UI
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static abstract class ExpenseFormEvent extends ComponentEvent<ExpenseForm> {
        private Expense expense;

        protected ExpenseFormEvent(ExpenseForm source, Expense expense) {
            super(source, false);
            this.expense = expense;
        }

        public Expense getExpense() {
            return expense;
        }
    }

    public static class SaveEvent extends ExpenseFormEvent {
        SaveEvent(ExpenseForm source, Expense expense) {
            super(source, expense);
        }
    }

    public static class DeleteEvent extends ExpenseFormEvent {
        DeleteEvent(ExpenseForm source, Expense expense) {
            super(source, expense);
        }
    }

    public static class CloseEvent extends ExpenseFormEvent {
        CloseEvent(ExpenseForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
