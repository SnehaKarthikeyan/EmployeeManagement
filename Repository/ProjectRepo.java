package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
