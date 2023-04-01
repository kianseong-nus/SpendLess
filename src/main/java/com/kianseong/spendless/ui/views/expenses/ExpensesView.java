package com.kianseong.spendless.ui.views.expenses;

import com.kianseong.spendless.backend.expense.ExpenseDTO;
import com.kianseong.spendless.backend.expense.ExpenseService;
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

    private final ExpenseService expenseService;
    private final Grid<ExpenseDTO> grid;
    private final TextField filterText;

    public ExpensesView(ExpenseService expenseService) {
        this.expenseService = expenseService;
        grid = new Grid<>(ExpenseDTO.class);
        filterText = new TextField();

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

        return new HorizontalLayout(filterText, addExpenseButton);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(ExpenseDTO::description).setHeader("Description");
        grid.addColumn(ExpenseDTO::category).setHeader("Category");
        grid.addColumn(ExpenseDTO::amount).setHeader("Amount");
        grid.addColumn(ExpenseDTO::date).setHeader("Date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true).setSortable(true));
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

    private void showExpenseForm(ExpenseDTO expenseDTO) {
        ExpenseForm form = new ExpenseForm(expenseService, expenseDTO);
        add(form);
        form.open();
    }

}
