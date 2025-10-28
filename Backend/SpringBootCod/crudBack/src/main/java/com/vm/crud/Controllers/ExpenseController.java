package com.vm.crud.Controllers;

import com.vm.crud.Services.ExpenseService;
import com.vm.crud.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    /**
     * Criar despesa e dividir automaticamente
     */
    @PostMapping
    public Expense createExpense(@RequestBody Map<String, String> dados) {
        Long groupId = Long.valueOf(dados.get("groupId"));
        Long payerId = Long.valueOf(dados.get("payerId"));
        String description = dados.get("description");
        BigDecimal amount = new BigDecimal(dados.get("amount"));

        return expenseService.createExpense(groupId, payerId, description, amount);
    }

    /**
     * Listar despesas de um grupo
     */
    @GetMapping("/group/{groupId}")
    public List<Expense> getExpenses(@PathVariable Long groupId) {
        return expenseService.getExpensesByGroup(groupId);
    }


    @PutMapping(path = "/group/{groupId}/edit/{expenseId}")
    public Expense editExpense (@RequestBody Map<String, String> data, Long groupId, Long expenseId) {
        Long payerId = Long.valueOf(data.get("payerId"));
        String description = data.get("description");
        BigDecimal amount = new BigDecimal(data.get("amount"));

        return expenseService.editExpenses(groupId, payerId, expenseId, description, amount);
    }

    @DeleteMapping (path = "/group/{groupId}/expense/{expenseId}")
    public void expenseDelete (@PathVariable Long expenseId, Long groupId) {
        expenseService.expenseDelete(expenseId, groupId);
    }

}