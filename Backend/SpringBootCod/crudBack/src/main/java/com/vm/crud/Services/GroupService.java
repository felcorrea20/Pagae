package com.vm.crud.Services;

import com.vm.crud.models.Group;
import com.vm.crud.models.GroupMember;
import com.vm.crud.models.User;
import com.vm.crud.repository.GroupMemberRepository;
import com.vm.crud.repository.GroupRepository;
import com.vm.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private UserRepository userRepository;

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
}