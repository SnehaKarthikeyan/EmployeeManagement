package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepo extends JpaRepository<Milestone, Long> {
    Milestone findByName(String name);
}
