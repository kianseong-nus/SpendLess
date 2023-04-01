package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import com.kianseong.spendless.backend.expense.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<ExpenseDTO> getExpenses() {
        return expenseService.findAllExpenses("");
    }

}
