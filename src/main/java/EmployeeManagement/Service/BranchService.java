package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.BranchRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.BranchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Branch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BranchService implements BranchServiceInterface {
    Logger logger = LoggerFactory.getLogger(BranchService.class);

    @Autowired
    BranchRepo repo;

    @Override
    public List<Branch> getAllBranch() {
        return repo.findAll();
    }

    @Override
    public Branch getById(long id) {
        Optional<Branch> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public Branch addBranch(Branch branch) {
        return repo.save(branch);
    }

    @Override
    public Branch updateBranch(Branch branch) {
        return repo.save(branch);
    }
}
