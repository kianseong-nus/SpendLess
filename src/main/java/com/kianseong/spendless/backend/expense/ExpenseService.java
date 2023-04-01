package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Transactional
    public List<ExpenseDTO> findAllExpenses(String filter) {
        if (filter == null || filter.isEmpty()) {
            return expenseRepository.findAll()
                    .stream()
                    .map(expenseMapper::expenseToDto)
                    .collect(Collectors.toList());
        } else {
            return expenseRepository.search(filter)
                    .stream()
                    .map(expenseMapper::expenseToDto)
                    .collect(Collectors.toList());
        }
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }

    @Transactional
    public void saveExpense(ExpenseDTO expenseDTO) {
        expenseRepository.save(expenseMapper.dtoToExpense(expenseDTO));
    }
}
