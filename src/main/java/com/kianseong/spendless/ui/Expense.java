package com.kianseong.spendless.ui;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

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
    private String description;
    private String category;
    private float amount;
    private LocalDate date;

    public Expense(Integer id, String description, String category, float amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.date = date;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return amount == expense.amount
                && Objects.equals(id, expense.id)
                && Objects.equals(description, expense.description)
                && Objects.equals(category, expense.category)
                && Objects.equals(date, expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, category, amount, date);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount + '\'' +
                ", date=" + date +
                '}';
    }
}
