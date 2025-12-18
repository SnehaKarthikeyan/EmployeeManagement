package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
}
