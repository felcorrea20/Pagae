package com.vm.crud.Controllers;

import com.vm.crud.Services.GroupService;
import com.vm.crud.models.Group;
import com.vm.crud.models.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * Criar grupo
     */
    @PostMapping
    public Group createGroup(@RequestBody Map<String, String> dados) {
        String name = dados.get("name");
        return groupService.createGroup(name);
    }

    /**
     * Adicionar membro ao grupo
     */
    @PostMapping("/{groupId}/members")
    public GroupMember addMember(@PathVariable Long groupId, @RequestBody Map<String, String> dados) {
        Long userId = Long.valueOf(dados.get("userId"));
        return groupService.addMember(groupId, userId);
    }

    /**
     * Listar membros do grupo
     */
    @GetMapping("/{groupId}/members")
    public List<GroupMember> getMembers(@PathVariable Long groupId) {
        return groupService.getMembers(groupId);
    }

    /**
     * Listar todos os grupos
     */
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}