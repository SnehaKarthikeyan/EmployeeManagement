package com.inzeph.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.BranchPurchase;

@Repository
public interface BranchPurchaseRepo extends JpaRepository<BranchPurchase, Long> {
  List<BranchPurchase> findByBranchId(Long id);
}
