package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
}
