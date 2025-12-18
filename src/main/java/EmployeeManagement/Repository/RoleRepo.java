package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByid(Long id);
}
