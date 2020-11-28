package com.travelingbastards.travelingspring.repository;

import com.travelingbastards.travelingspring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    List<Role> findAll();
}
