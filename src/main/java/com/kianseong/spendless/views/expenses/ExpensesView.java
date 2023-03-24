package com.kianseong.spendless.views.expenses;

import com.kianseong.spendless.data.Expense;
import com.kianseong.spendless.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Expenses")
@Route(value = "expenses", layout = MainLayout.class)
public class ExpensesView extends VerticalLayout {

    Grid<Expense> grid;

    public ExpensesView() {
        grid = new Grid<>(Expense.class);

        setSizeFull();
        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("description", "category", "amount");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }
}
