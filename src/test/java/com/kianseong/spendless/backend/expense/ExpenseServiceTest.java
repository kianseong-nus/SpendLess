package com.kianseong.spendless.backend.expense;

import com.kianseong.spendless.ui.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ExpenseServiceTest {

    private ExpenseRepository repository;
    private ExpenseService service;

    @BeforeEach
    void setUp() {
        repository = mock(ExpenseRepository.class);
        service = new ExpenseService(repository);
    }

    @Test
    void saveExpense_ShouldSaveExpense() {
        final Expense expenseToSave = new Expense(
                "Breakfast",
                "Food",
                30,
                LocalDate.of(2022, 02, 02)
        );
        when(service.saveExpense(any(Expense.class))).thenReturn(expenseToSave);

        final Expense actual = service.saveExpense(expenseToSave);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expenseToSave);
        verify(repository, times(1)).save(any(Expense.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteExpense_ShouldDeleteExpense_ExpenseIsPresent() {
        // TODO
        final Expense expense = new Expense(
                "Breakfast",
                "Food",
                30,
                LocalDate.of(2022, 02, 02)
        );
        service.saveExpense(expense);
        service.deleteExpense(expense);
        verify(repository, times(1)).delete(expense);
    }

}
