package com.kianseong.spendless.backend.expense;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Expense {

    @Id
    @SequenceGenerator(
            name = "expense_id_seq",
            sequenceName = "expense_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_id_seq"
    )
    private Integer id;
    /**
     * Description of expense.
     */
    private String description;
    private String category;
    private boolean isIncome;
    private float amount;
    private LocalDate date;

    public Expense(final Integer id,
                   final String description,
                   final String category,
                   final boolean isIncome,
                   final float amount,
                   final LocalDate date) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.isIncome = isIncome;
        this.amount = amount;
        this.date = date;
    }

    public Expense() {

    }

    public Expense(final String description,
                   final String category,
                   final boolean isIncome,
                   final float amount,
                   final LocalDate date) {
        this.description = description;
        this.category = category;
        this.isIncome = isIncome;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public boolean getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(final boolean isIncome) {
        this.isIncome = isIncome;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(final float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", category='" + category + '\''
                + ", amount=" + amount + '\''
                + ", date=" + date
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return isIncome == expense.isIncome
                && Float.compare(expense.amount, amount) == 0
                && Objects.equals(id, expense.id)
                && Objects.equals(description, expense.description)
                && Objects.equals(category, expense.category)
                && Objects.equals(date, expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, category, isIncome, amount, date);
    }
}
