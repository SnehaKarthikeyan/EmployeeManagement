package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.BranchPurchaseServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.BranchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Branch;
import com.inzeph.EmployeeManagement.Model.BranchPurchase;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/branchtransaction")
public class BranchPurchaseController {
    Logger logger = LoggerFactory.getLogger(BranchPurchaseController.class);

    @Autowired
    BranchPurchaseServiceInterface service;

    @Autowired
    BranchServiceInterface branchService;

    @GetMapping
    public ResponseEntity<Object> getBranchTransaction() throws Exception {
        List<BranchPurchase> branchTransactions = service.getAllBranchTransaction();
        if (branchTransactions.isEmpty()) {
            return Util.generateResponse("Transaction was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved all branchTransaction", HttpStatus.OK, branchTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByBranchId(@PathVariable("id") long id) throws Exception {
        if (branchService.getById(id) == null) {
            return Util.generateResponse("Branch " + id + " was Not Found", HttpStatus.NOT_FOUND, null);
        }
        List<BranchPurchase> branchTransactions = service.getByBranchId(id);
        if (branchTransactions == null) {
            return Util.generateResponse("No Transactions were made in Branch " + id, HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved branchTransactions " + id, HttpStatus.OK,
                branchTransactions);
    }

    @PostMapping
    public ResponseEntity<Object> createBranchTransaction(@RequestBody BranchPurchase branchTransaction)
            throws Exception {
        Branch branch = branchService.getById(branchTransaction.getBranchId());
        if (branch == null) {
            return Util.generateResponse("Branch " + branchTransaction.getBranchId() + " was not found",
                    HttpStatus.BAD_REQUEST, null);
        }
        branch.setAmount(branch.getAmount() - branchTransaction.getAmount());
        branchService.updateBranch(branch);
        BranchPurchase response = service.addBranchTransaction(branchTransaction);
        return Util.generateResponse("Successfully added transaction " + branchTransaction.getId(), HttpStatus.CREATED,
                response);
    }
}
