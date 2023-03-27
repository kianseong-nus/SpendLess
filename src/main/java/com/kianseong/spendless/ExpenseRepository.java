package com.kianseong.spendless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("select e from Expense e " +
            "where lower(e.description) like lower(concat('%', :searchTerm, '%')) ")
    List<Expense> search(@Param("searchTerm") String searchTerm);

}
