package com.fdmgroup.CharCloud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.repository.RoleRepository;
import com.fdmgroup.CharCloud.security.Roles;


@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository repo;

	
	@Override
	public Role findRoleByName(Roles roleName) {
		Optional<Role> optRole = repo.findByRole(roleName);
		return optRole.orElse(new Role(Roles.Default));
	}
}

