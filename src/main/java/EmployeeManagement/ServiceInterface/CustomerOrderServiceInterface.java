package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.CustomerOrder;

public interface CustomerOrderServiceInterface {
    CustomerOrder getCustomerOrderById(Long id);

    List<CustomerOrder> getAllCustomerOrder();

    CustomerOrder addCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder updateCustomerOrder(CustomerOrder customerOrder);

}
