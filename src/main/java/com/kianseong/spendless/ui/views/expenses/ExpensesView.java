package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.services.ExpenseService;
import com.kianseong.spendless.ui.Expense;
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
    TextField filterText;
    NewExpenseView newExpense;

    public ExpensesView(ExpenseService service) {
        this.service = service;
        grid = new Grid<>(Expense.class);
        filterText = new TextField();
        newExpense = new NewExpenseView();

        setSizeFull();
        configureGrid();
        add(getToolbar(), getContent(), newExpense);
        updateList();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(1, grid);
        content.setSizeFull();
        return content;
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by description...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addExpenseButton = new Button("Add expense");
        addExpenseButton.addClickListener(e -> showNewExpenseForm());

        return new HorizontalLayout(filterText, addExpenseButton);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("description", "category", "amount", "date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


    private void updateList() {
        grid.setItems(service.findAllExpenses(filterText.getValue()));
    }


    public void showNewExpenseForm() {
        newExpense.open();
    }
}
