package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.CustomerServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Customer;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getAllCustomer() {
        List<Customer> customer = service.getAllCustomer();
        if (customer.isEmpty()) {
            return Util.generateResponse("Customer was not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved customers!", HttpStatus.OK, customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable long id) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return Util.generateResponse("Customer " + id + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved customer!", HttpStatus.OK, customer);
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        return Util.generateResponse("Successfully created customer!", HttpStatus.CREATED,
                service.addCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody Customer updateCustomer) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return Util.generateResponse("Customer " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateCustomer, customer);
        customer.setId(id);
        return Util.generateResponse("successfully Updated customer", HttpStatus.OK, service.updateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return Util.generateResponse("Customer " + id + " was not found", HttpStatus.BAD_REQUEST, null);
        }
        service.deleteCustomer(customer);
        return Util.generateResponse("Successfully deleted the customer!", HttpStatus.OK, id);
    }
}
