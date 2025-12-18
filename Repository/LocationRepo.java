package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {
    Location findByEmpId(Long empId);
}
