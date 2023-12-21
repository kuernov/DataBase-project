package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
