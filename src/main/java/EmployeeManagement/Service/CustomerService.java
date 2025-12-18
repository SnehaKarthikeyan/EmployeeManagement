package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.CustomerRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService implements CustomerServiceInterface {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepo repo;

    @Override
    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = repo.findById(id);
        return customer.isPresent() ? customer.get() : null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        repo.delete(customer);
    }
}
