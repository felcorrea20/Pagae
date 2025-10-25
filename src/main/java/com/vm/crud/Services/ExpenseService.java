package com.vm.crud.Services;

import com.vm.crud.models.*;
import com.vm.crud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Método principal: Criar despesa e dividir entre os membros
     */
    public Expense createExpense(Long groupId, Long payerId, String description, BigDecimal amount) {

        // 1. Buscar o grupo
        Group group = groupRepository.findById(groupId).get();

        // 2. Buscar quem pagou
        User payer = userRepository.findById(payerId).get();

        // 3. Criar a despesa
        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setGroup(group);
        expense.setPayer(payer);

        expenseRepository.save(expense);

        // 4. Buscar membros do grupo
        List<GroupMember> members = groupMemberRepository.findByGroupId(groupId);

        // 5. DIVIDIR: calcular quanto cada um deve
        int totalPessoas = members.size();
        BigDecimal valorPorPessoa = amount.divide(BigDecimal.valueOf(totalPessoas), 2, RoundingMode.HALF_UP);

        // 6. Criar as divisões (splits)
        for (GroupMember member : members) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(expense);
            split.setUser(member.getUser());
            split.setAmount(valorPorPessoa);

            expenseSplitRepository.save(split);
        }

        return expense;
    }

    /**
     * Listar despesas de um grupo
     */
    public List<Expense> getExpensesByGroup(Long groupId) {
        return expenseRepository.findByGroupId(groupId);
    }
}