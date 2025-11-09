package com.vm.crud.Services;

import com.vm.crud.exceptions.ResourceNotFoundException;
import com.vm.crud.models.*;
import com.vm.crud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    /**
     * Criar um grupo
     */
    public Group createGroup(String name) {
        Group group = new Group();
        group.setName(name);
        return groupRepository.save(group);
    }

    /**
     * Adicionar membro ao grupo
     */
    public GroupMember addMember(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId).get();
        User user = userRepository.findById(userId).get();

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);

        return groupMemberRepository.save(member);
    }

    /**
     * Listar membros de um grupo
     */
    public List<GroupMember> getMembers(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }

    /**
     * Listar todos os grupos
     */
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group editGroup(Long id, Group group) {
        Group grp = groupRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Group " + id + " not found"));

        if (group.getName() != null && group.getName() != "") {
            grp.setName(group.getName());
        }

        return groupRepository.save(grp);
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupMemberRepository.deleteByGroupId(id);
        groupRepository.deleteById(id);
    }

    @Transactional
    public Group removeMember(Long groupId, Long memberId) {
        groupMemberRepository.deleteById(memberId);

        Group gp = groupRepository.findById(groupId).get();

        List<Expense> expenses = expenseRepository.findByGroupId(groupId);

        List<GroupMember> gm = groupMemberRepository.findByGroupId(groupId);

        int QTDMembers = gm.size();

        for (Expense expense : expenses) {

            BigDecimal valuePerPerson = expense.getAmount().divide(BigDecimal.valueOf(QTDMembers), 2, RoundingMode.HALF_UP);

            expenseSplitRepository.deleteByExpenseId(expense.getId());

            for (GroupMember member : gm) {
                ExpenseSplit split = new ExpenseSplit();
                split.setExpense(expense);
                split.setUser(member.getUser());
                split.setAmount(valuePerPerson);

                expenseSplitRepository.save(split);
            }
        }

        return groupRepository.save(gp);
    }
}