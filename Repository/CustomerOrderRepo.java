package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.CustomerOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {
}
