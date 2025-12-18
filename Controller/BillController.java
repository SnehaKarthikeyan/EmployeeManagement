package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.BillServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inzeph.EmployeeManagement.Model.Bill;
import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Role;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/bill")
public class BillController {
    Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    BillServiceInterface service;

    @Autowired
    EmployeeServiceInterface employeeService;

    String[] strArray = { "bankDetails", "contact", "aadhar", "address", "birthDate", "bloodGroup", "password",
            "createdTime", "isActive", "joiningDate", "designation", "email", "employeeLeaveCount"};
    List<String> fields = Arrays.asList(strArray);

    @GetMapping
    public ResponseEntity<Object> getBill() throws Exception {
        List<Bill> bills = service.getAllBills();
        if (bills.isEmpty()) {
            return Util.generateResponse("Bill was not Found", HttpStatus.NO_CONTENT, null);
        }
        ArrayList<Object> json = new ArrayList<>();
        Object resultantEmployee;
        Employee employee;
        Role role;
        for (Bill bill : bills) {
            employee = employeeService.getById(bill.getEmployee());
            role = employeeService.getByRoleId(employee.getRole());
            resultantEmployee = Util.convertJSON(Util.removeJSONFields(employee, fields), role, "role");
            json.add(Util.convertJSON(bill, resultantEmployee, "employee"));
        }
        return Util.generateResponse("Successfully retrieved all bills", HttpStatus.OK, json);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByBillId(@PathVariable("id") long id) throws Exception {
        Bill bill = service.getById(id);
        if (bill == null) {
            return Util.generateResponse("Bill " + id + " was Not Found", HttpStatus.NOT_FOUND, null);
        }
        Employee employee = employeeService.getById(bill.getEmployee());
        Role role = employeeService.getByRoleId(employee.getRole());
        return Util.generateResponse("Successfully retrieved bill " + id, HttpStatus.OK,
                Util.convertJSON(bill, Util.convertJSON(Util.removeJSONFields(employee, fields), role, "role"), "employee"));
    }

    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody Bill bill) throws Exception {
        if (bill.getBillItems() == null) {
            return Util.generateResponse("Items not found", HttpStatus.NOT_FOUND, null);
        }
        Bill response = service.addBill(bill);
        Employee employee = employeeService.getById(response.getEmployee());
        Role role = employeeService.getByRoleId(employee.getRole());
        return Util.generateResponse("Successfully created bill " + bill.getId(), HttpStatus.CREATED,
                Util.convertJSON(response, Util.convertJSON(Util.removeJSONFields(employee, fields), role, "role"), "employee"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBill(@PathVariable("id") Long id, @RequestBody Bill bill) throws Exception {
        Bill updatedBill = service.getById(id);
        if (updatedBill == null) {
            return Util.generateResponse("Bill " + id + " was not found", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(bill, updatedBill);
        updatedBill.setId(id);
        Employee employee = employeeService.getById(updatedBill.getEmployee());
        Role role = employeeService.getByRoleId(employee.getRole());
        return Util.generateResponse("Successfully updated bill " + bill.getId(), HttpStatus.OK,
                Util.convertJSON(service.updateBill(updatedBill), Util.convertJSON(Util.removeJSONFields(employee, fields), role, "role"), "employee"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBill(@PathVariable("id") long id) {
        Bill bill = service.getById(id);
        if (bill == null) {
            return Util.generateResponse("Bill " + id + " was not found", HttpStatus.NOT_FOUND, null);
        }
        service.deleteBill(bill);
        return Util.generateResponse("Successfully deleted bill " + bill.getId(), HttpStatus.OK, id);
    }
}
