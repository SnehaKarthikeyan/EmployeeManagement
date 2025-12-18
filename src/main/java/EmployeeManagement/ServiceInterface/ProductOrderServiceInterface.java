package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductOrder;

public interface ProductOrderServiceInterface {
    List<ProductOrder> getAllOrders();
    ProductOrder getById(Long id);
    ProductOrder addOrder(ProductOrder projectOrder);
    void deleteOrder(ProductOrder projectOrder);
    ProductOrder updateOrder(ProductOrder ProductOrder);
    List<ProductOrder> getByName(String name);
}
