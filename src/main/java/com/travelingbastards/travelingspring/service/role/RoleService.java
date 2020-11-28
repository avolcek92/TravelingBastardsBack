package com.travelingbastards.travelingspring.service.role;

import com.travelingbastards.travelingspring.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findByRoleName(String roleName);
    Role findById(Long id);
}
