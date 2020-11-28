package com.travelingbastards.travelingspring.service.role;

import com.travelingbastards.travelingspring.model.Role;
import com.travelingbastards.travelingspring.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        return role;
    }

    @Override
    public Role findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID: " + id));
        return role;
    }
}
