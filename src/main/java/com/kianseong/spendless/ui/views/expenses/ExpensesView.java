package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.ui.ExpenseForm;
import com.kianseong.spendless.ui.Expense;
import com.kianseong.spendless.backend.services.ExpenseService;
import com.kianseong.spendless.ui.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Expenses")
@Route(value = "expenses", layout = MainLayout.class)
public class ExpensesView extends VerticalLayout {

    ExpenseService service;
    Grid<Expense> grid;
    ExpenseForm form;
    TextField filterText;

    public ExpensesView(ExpenseService service) {
        this.service = service;
        grid = new Grid<>(Expense.class);
        filterText = new TextField();

        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        closeEditor();
        updateList();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void saveExpense(ExpenseForm.SaveEvent event) {
        service.saveExpense(event.getExpense());
        updateList();
        closeEditor();
    }

    private void deleteExpense(ExpenseForm.DeleteEvent event) {
        service.deleteExpense(event.getExpense());
        updateList();
        closeEditor();
    }

    private void closeExpense(ExpenseForm.CloseEvent event) {
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by description...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addExpenseButton = new Button("Add expense");
        addExpenseButton.addClickListener(click -> addExpense());

        return new HorizontalLayout(filterText, addExpenseButton);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("description", "category", "amount");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editExpense(event.getValue()));
    }

    private void configureForm() {
        form = new ExpenseForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveExpense);
        form.addDeleteListener(this::deleteExpense);
        form.addCloseListener(this::closeExpense);
    }

    private void addExpense() {
        grid.asSingleSelect().clear();
        editExpense(new Expense());
    }

    public void editExpense(Expense expense) {
        if (expense == null) {
            closeEditor();
        } else {
            form.setExpense(expense);
            form.setVisible(true);
        }
    }

    private void updateList() {
        grid.setItems(service.findAllExpenses(filterText.getValue()));
    }

    private void closeEditor() {
        form.setExpense(null);
        form.setVisible(false);
    }
}
