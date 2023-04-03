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

    @Transactional
    public List<Expense> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll();
        } else {
            return expenseRepository.search(filter);
        }
    }

    @Transactional
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    @Transactional
    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }
}
