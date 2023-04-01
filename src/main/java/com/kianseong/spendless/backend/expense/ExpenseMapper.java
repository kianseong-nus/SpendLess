package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import org.springframework.stereotype.Service;


@Service
public class ExpenseMapper {

    public ExpenseDTO expenseToDto(Expense expense) {
        return new ExpenseDTO(
                expense.getDescription(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getDate()
        );
    }

    public Expense dtoToExpense(ExpenseDTO expenseDTO) {
        return new Expense(
                expenseDTO.description(),
                expenseDTO.category(),
                expenseDTO.amount(),
                expenseDTO.date()
        );
    }

}
