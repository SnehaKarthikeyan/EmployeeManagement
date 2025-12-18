package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Customer;

public interface CustomerServiceInterface {
    Customer getCustomerById(Long id);

    List<Customer> getAllCustomer();

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}
