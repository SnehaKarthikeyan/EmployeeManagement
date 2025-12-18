package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.BranchPurchaseRepo;
import com.inzeph.EmployeeManagement.Repository.BranchRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.BranchPurchaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.BranchPurchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BranchPurchaseService implements BranchPurchaseServiceInterface {
    Logger logger = LoggerFactory.getLogger(BranchPurchaseService.class);

    @Autowired
    BranchPurchaseRepo repo;

    @Autowired
    BranchRepo branchRepo;

    @Override
    public BranchPurchase addBranchTransaction(BranchPurchase branchTransaction) {
        return repo.save(branchTransaction);
    }

    @Override
    public List<BranchPurchase> getAllBranchTransaction() {
        return repo.findAll();
    }

    @Override
    public List<BranchPurchase> getByBranchId(long id) {
        return repo.findByBranchId(id);
    }
}
