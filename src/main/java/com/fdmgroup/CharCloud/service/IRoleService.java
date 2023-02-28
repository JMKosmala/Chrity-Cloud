package com.fdmgroup.CharCloud.service;

import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.security.Roles;

public interface IRoleService {
	Role findRoleByName(Roles roleName);
}
