package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Branch;

public interface BranchServiceInterface {
    List<Branch> getAllBranch();
    Branch getById(long id);
    Branch addBranch(Branch branch);
    Branch updateBranch(Branch branch);
}
