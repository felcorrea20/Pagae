package com.vm.crud.repository;

import com.vm.crud.models.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findByGroupId(Long groupId);
}