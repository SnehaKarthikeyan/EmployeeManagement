package com.inzeph.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployee(long id);
}
