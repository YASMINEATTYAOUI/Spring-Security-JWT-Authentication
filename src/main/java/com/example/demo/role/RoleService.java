package com.example.demo.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    void createRole(Role roleRequest);
    Role updateRole(Role role);
    Role getRoleById(Long id);
    Page<Role> getAllRolesSortedByCreationDate(Pageable pageable);
    Page<Role> getAllRolesByCreatorIdSortedByCreationDate(Long creatorId, String name, Pageable pageable);
    Page<Role> searchRolesByName(String keyword, Pageable pageable);

    void deleteRoleById(Long id);
    void deleteMultipleRolesByIds(List<Long> ids);

    long countRoles();
}
