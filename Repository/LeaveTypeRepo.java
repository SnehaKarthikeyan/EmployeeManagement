package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.LeaveType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepo extends JpaRepository<LeaveType, Long> {
}
