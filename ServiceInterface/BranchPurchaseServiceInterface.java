package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.BranchPurchase;

public interface BranchPurchaseServiceInterface {
    BranchPurchase addBranchTransaction(BranchPurchase branchTransaction);
    List<BranchPurchase> getAllBranchTransaction();
    List<BranchPurchase> getByBranchId(long id);
}
