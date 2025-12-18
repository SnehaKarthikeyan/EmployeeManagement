package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.ProductOrderRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductOrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.ProductOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductOrderService implements ProductOrderServiceInterface {
    Logger logger = LoggerFactory.getLogger(ProductOrderService.class);

    @Autowired
    ProductOrderRepo repo;

    @Override
    public ProductOrder addOrder(ProductOrder productOrder) {
        return repo.save(productOrder);
    }

    @Override
    public List<ProductOrder> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public ProductOrder getById(Long id) {
        Optional<ProductOrder> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public ProductOrder updateOrder(ProductOrder productOrder) {
        return repo.save(productOrder);
    }

    @Override
    public List<ProductOrder> getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public void deleteOrder(ProductOrder productOrder) {
        repo.delete(productOrder);
    }

}
