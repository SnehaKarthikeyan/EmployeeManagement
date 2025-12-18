package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.PurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
}
