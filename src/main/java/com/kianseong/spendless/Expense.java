package com.kianseong.spendless;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Expense {

    @Id
    @SequenceGenerator(
            name = "expense_id_sequence",
            sequenceName = "expense_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_id_sequence"
    )
    private Integer id;
    private String description;
    private String category;
    private int amount;

    public Expense(Integer id, String description, String category, int amount) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public Expense() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return amount == expense.amount && Objects.equals(id, expense.id) && Objects.equals(description, expense.description) && Objects.equals(category, expense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, category, amount);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
