package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExpenseDTOMapper implements Function<Expense, ExpenseDTO> {

    @Override
    public ExpenseDTO apply(Expense expense) {
        return new ExpenseDTO(
                expense.getDescription(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getDate()
        );
    }

}
