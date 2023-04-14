package com.kianseong.spendless.backend.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    /**
     * Gets all expenses with description containing the search term.
     * @param searchTerm keyword to filter
     * @return List of expenses
     */
    @Query("select e from Expense e "
            + "where lower(e.description) "
            + "like lower(concat('%', :searchTerm, '%')) ")
    List<Expense> filter(@Param("searchTerm") String searchTerm);

}
