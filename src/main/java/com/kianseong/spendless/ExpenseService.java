package com.kianseong.spendless;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public void saveExpense(Expense expense) {
        if (expense == null) {
            System.err.println("Expense is empty!");
            return;
        }
        expenseRepository.save(expense);
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }

    public List<Expense> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll();
        } else {
            return expenseRepository.search(filter);
        }
    }
}
