package com.kianseong.spendless.ui;

import java.util.Objects;

public class ExpenseCategory {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseCategory expenseCategory = (ExpenseCategory) o;
        return Objects.equals(name, expenseCategory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
