package com.inzeph.EmployeeManagement.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.CustomerOrder;
import com.inzeph.EmployeeManagement.Repository.CustomerOrderRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.CustomerOrderServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerOrderService implements CustomerOrderServiceInterface {
    Logger logger = LoggerFactory.getLogger(CustomerOrderService.class);

    @Autowired
    CustomerOrderRepo repo;

    @Override
    public List<CustomerOrder> getAllCustomerOrder() {
        return repo.findAll();
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long id) {
        Optional<CustomerOrder> order = repo.findById(id);
        return order.isPresent() ? order.get() : null;
    }

    @Override
    public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
        return repo.save(customerOrder);
    }

    @Override
    public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
        return repo.save(customerOrder);
    }

}
