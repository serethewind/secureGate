package com.example.Spring.Security.repository;

import com.example.Spring.Security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRolename(String rolename);
}
