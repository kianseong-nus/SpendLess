package com.kianseong.spendless.ui.views.statistics;

import com.kianseong.spendless.backend.expense.ExpenseService;
import com.kianseong.spendless.ui.views.MainLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.HashMap;
import java.util.Map;

@PageTitle("Statistics")
@Route(value = "statistics", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class StatisticsView extends VerticalLayout {

    private ExpenseService expenseService;
    private final Chart chart = new Chart(ChartType.PIE);
    private final HashMap<String, Float> dataMap = new HashMap<>();
    private final DataSeries series = new DataSeries();

    public StatisticsView(final ExpenseService expenseService) {
        this.expenseService = expenseService;
        createChart();
        updateData();
        setSizeFull();
        add(chart, createDataTable());
    }

    private void updateData() {
        expenseService.findAllExpenses("")
                .stream()
                .filter(e -> !e.getIsIncome())
                .forEach(e -> dataMap.put(e.getCategory(), dataMap.getOrDefault(e.getCategory(), 0.0f) + e.getAmount()));
    }

    private void createChart() {
        chart.getConfiguration().addSeries(series);
    }

    private VerticalLayout createDataTable() {
        String stats = "";
        for (Map.Entry<String, Float> set : dataMap.entrySet()) {
            series.add(new DataSeriesItem(set.getKey(), set.getValue()));
            stats += set.getKey() + " : " + set.getValue() + "\n";
        }
        TextArea textArea = new TextArea();
        textArea.setValue(stats);
        VerticalLayout layout = new VerticalLayout();
        textArea.setWidthFull();
        textArea.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
        layout.add(textArea);
        return layout;
    }
}
