package com.build.energy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.energy.security.entity.ERole;
import com.build.energy.security.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
