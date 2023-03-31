package com.kianseong.spendless.backend.services;

import com.kianseong.spendless.ui.Expense;
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

    @Transactional
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }
}
