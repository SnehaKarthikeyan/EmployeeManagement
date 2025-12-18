package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.PurchaseOrderRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.PurchaseOrderServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.PurchaseOrder;

@Service
public class PurchaseOrderService implements PurchaseOrderServiceInterface {
    Logger logger = LoggerFactory.getLogger(PurchaseOrderService.class);

    @Autowired
    PurchaseOrderRepo repo;

    @Override
    public List<PurchaseOrder> getAllPurchaseOrder() {
        return repo.findAll();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        Optional<PurchaseOrder> order = repo.findById(id);
        return order.isPresent() ? order.get() : null;
    }

    @Override
    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        return repo.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        return repo.save(purchaseOrder);
    }

}
