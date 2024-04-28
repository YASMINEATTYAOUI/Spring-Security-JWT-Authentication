package com.example.demo.role;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import com.example.demo.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void createRole(Role role) {

        roleRepository.save(role);
        //log.info("Role {} is saved", role.getId());
    }

    @Override
    public Role updateRole(Role updatedRole) {
        /**
         Role existingRole = roleRepository.findById(updatedRole.getId())
         .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Role with ID " + updatedRole.getId() + " not found"));

         Role updatedRole = roleRepository.save(existingRole);
         log.info("Role {} got updated", updatedRole.getId());
         */
        return updatedRole;
    }

    @Override
    public Role getRoleById(Long id) {
/*
        Role role = roleRepository.findById(id);

         .orElseThrow(() -> new NotFoundException("Role with ID " + id + " not found"));
 // log.info("Role {} is fetched", role.getId());
 */
        return null;
    }

    @Override
    public Page<Role> getAllRolesSortedByCreationDate(Pageable pageable) {
        // log.info("Retrieving All Roles (Sorted)");
        return roleRepository.findAllByOrderByCreationDateDesc(pageable);
    }

    @Override
    public Page<Role>  getAllRolesByCreatorIdSortedByCreationDate(Long creatorId, String name, Pageable pageable) {

        if(name != null){
            return roleRepository.findByCreatorIdAndNameContainingIgnoreCaseOrderByCreationDate(creatorId, name, pageable);
        }
        return roleRepository.findByCreatorIdOrderByCreationDate(creatorId, pageable);
    }

    @Override
    public Page<Role> searchRolesByName(String keyword, Pageable pageable) {
        return roleRepository.findByNameContainingIgnoreCaseOrderByCreationDateDesc(keyword, pageable);
    }

    @Override
    public void deleteRoleById(Long id) {

         Role role = roleRepository.findById(id)
         .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
         roleRepository.delete(role);
         //log.info("Role {} is deleted", role.getId());

    }

    @Override
    public void deleteMultipleRolesByIds(List<Long> ids) {

        // log.info("Batch deletion of roles with IDs: {}", ids);
        roleRepository.deleteAllById(ids);
    }
    @Override
    public long countRoles() {
        return roleRepository.count();
    }

}
