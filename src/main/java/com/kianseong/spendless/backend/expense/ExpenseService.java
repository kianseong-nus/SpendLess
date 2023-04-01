package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import com.kianseong.spendless.backend.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseDTOMapper expenseDTOMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
    }

    @Transactional
    public List<ExpenseDTO> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll()
                    .stream()
                    .map(expenseDTOMapper)
                    .collect(Collectors.toList());
        } else {
            return expenseRepository.search(filter)
                    .stream()
                    .map(expenseDTOMapper)
                    .collect(Collectors.toList());
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
