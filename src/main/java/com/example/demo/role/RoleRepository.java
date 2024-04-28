package com.example.demo.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Page<Role> findAllByOrderByCreationDateDesc(Pageable pageable);
    Page<Role> findByNameContainingIgnoreCaseOrderByCreationDateDesc(String name, Pageable pageable);
    Page<Role> findByCreatorIdOrderByCreationDate(Long creatorId, Pageable pageable);
    Page<Role> findByCreatorIdAndNameContainingIgnoreCaseOrderByCreationDate(Long creatorId, String name, Pageable pageable);

    Role findByName(String name);
}
