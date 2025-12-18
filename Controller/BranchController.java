package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.BranchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Branch;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/branch")
public class BranchController {
    Logger logger = LoggerFactory.getLogger(BranchController.class);

    @Autowired
    BranchServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getBranchTransaction() throws Exception {
        List<Branch> branch = service.getAllBranch();
        if (branch.isEmpty()) {
            return Util.generateResponse("Branch was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved all branches", HttpStatus.OK, branch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) throws Exception {
        Branch branch = service.getById(id);
        if (branch == null) {
            return Util.generateResponse("Branch " + id + " was Not Found", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved branch " + id, HttpStatus.OK, branch);
    }

    @PostMapping
    public ResponseEntity<Object> createBranch(@RequestBody Branch branch) throws Exception {
        Branch response = service.addBranch(branch);
        return Util.generateResponse("Successfully added branch " + branch.getId(), HttpStatus.CREATED, response);
    }
}
