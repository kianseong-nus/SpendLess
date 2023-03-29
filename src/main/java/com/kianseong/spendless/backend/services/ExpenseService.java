package com.kianseong.spendless.backend.services;

import com.kianseong.spendless.ui.Expense;
import com.kianseong.spendless.ui.ExpenseCategory;
import com.kianseong.spendless.backend.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public List<Expense> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll();
        } else {
            return expenseRepository.search(filter);
        }
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }

    public void saveExpense(Expense expense) {
        if (expense == null) {
            System.err.println("Expense is empty!");
            return;
        }
        expenseRepository.save(expense);
    }

    public List<ExpenseCategory> getAllCategories() {
        ExpenseCategory food = new ExpenseCategory();
        food.setName("Food");
        ExpenseCategory transport = new ExpenseCategory();
        transport.setName("Transport");
        ExpenseCategory personal = new ExpenseCategory();
        personal.setName("Personal");
        return List.of(food, transport, personal);
    }
}
