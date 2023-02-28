package com.fdmgroup.CharCloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.security.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRole(Roles role);
}
