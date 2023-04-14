package com.kianseong.spendless.backend.expense;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> findAllExpenses(final String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll();
        } else {
            return expenseRepository.filter(filter);
        }
    }

    @Transactional
    public Expense saveExpense(final Expense expense) {
        expenseRepository.save(expense);
        return expense;
    }

    @Transactional
    public void deleteExpense(final Expense expense) {
        expenseRepository.delete(expense);
    }
}
