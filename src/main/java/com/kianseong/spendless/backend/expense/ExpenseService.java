package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll();
        } else {
            return expenseRepository.filter(filter);
        }
    }

    @Transactional
    public Expense saveExpense(Expense expense) {
        expenseRepository.save(expense);
        return expense;
    }

    @Transactional
    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }
}
