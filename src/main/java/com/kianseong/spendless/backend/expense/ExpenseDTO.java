package com.kianseong.spendless.backend.expense;

import java.time.LocalDate;

public record ExpenseDTO(
        String description,
        String category,
        float amount,
        LocalDate date
) {}
