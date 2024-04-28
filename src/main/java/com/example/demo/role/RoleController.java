package com.example.demo.role;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.role.RoleService;
import com.example.demo.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@RequestBody Role role) {
        roleService.createRole(role);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@RequestBody Role updatedRole) {
        return roleService.updateRole(updatedRole);
    }

    @GetMapping("/sorted")
    @ResponseStatus(HttpStatus.OK)
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleService.getAllRolesSortedByCreationDate(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRoleById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteMultipleRoles(@RequestParam List<Long> ids) {
        roleService.deleteMultipleRolesByIds(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<Role> searchRolesByKeyword(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        return roleService.searchRolesByName(keyword, pageable);
    }

    @GetMapping("/creatorId/{creatorId}")
    public Page<Role> getAllRolesByCreatorIdSortedByCreationDate(@PathVariable Long creatorId,
                                                                          @RequestParam(name = "reference", required = false) String reference,
                                                                          Pageable pageable){
        return roleService.getAllRolesByCreatorIdSortedByCreationDate(creatorId, reference, pageable);
    }

    @GetMapping("/count")
    public long countRoles(){
        return roleService.countRoles();
    }
}
