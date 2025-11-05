package com.vm.crud.repository;

import com.vm.crud.models.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    public void deleteByExpenseId (Long expenseId);
}