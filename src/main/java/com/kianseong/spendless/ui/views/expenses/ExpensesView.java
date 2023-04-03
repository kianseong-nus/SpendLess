package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseService;
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
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("Expenses")
@Route(value = "expenses", layout = MainLayout.class)
public class ExpensesView extends VerticalLayout {

    private final ExpenseService expenseService;
    private final Grid<Expense> grid = new Grid<>(Expense.class);
    private final TextField filterText = new TextField();

    public ExpensesView(ExpenseService expenseService) {
        this.expenseService = expenseService;

        setSizeFull();
        configureGrid();
        add(getToolbar(), getContent());
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
        Button refreshButton = new Button(LineAwesomeIcon.UNDO_ALT_SOLID.create());
        refreshButton.addClickListener(e -> updateList());

        return new HorizontalLayout(filterText, addExpenseButton, refreshButton);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("description", "category", "amount", "date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addItemDoubleClickListener(e -> showExpenseForm(e.getItem()));
    }

    private void updateList() {
        grid.setItems(expenseService.findAllExpenses(filterText.getValue()));
    }

    private void showNewExpenseForm() {
        ExpenseForm form = new ExpenseForm(expenseService);
        add(form);
        form.open();
    }

    private void showExpenseForm(Expense expense) {
        ExpenseForm form = new ExpenseForm(expenseService, expense);
        add(form);
        form.open();
    }

}
