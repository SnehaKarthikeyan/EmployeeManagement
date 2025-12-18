package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.PurchaseOrderServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.CashBox;
import com.inzeph.EmployeeManagement.Model.PurchaseOrder;
import com.inzeph.EmployeeManagement.Utils.Util;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
    Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    @Autowired
    PurchaseOrderServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getAllPurchaseOrder() {
        List<PurchaseOrder> purchaseOrders = service.getAllPurchaseOrder();
        if (purchaseOrders.isEmpty()) {
            return Util.generateResponse("Purchase Order was not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved Purchase Order!", HttpStatus.OK, purchaseOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPurchaseOrderById(@PathVariable long id) {
        PurchaseOrder purchaseOrder = service.getPurchaseOrderById(id);
        if (purchaseOrder == null) {
            return Util.generateResponse("Purchase Order " + id + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved Purchase Order!", HttpStatus.OK, purchaseOrder);
    }

    @PostMapping
    public ResponseEntity<Object> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return Util.generateResponse("Successfully created an order", HttpStatus.CREATED,
                service.addPurchaseOrder(purchaseOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder updateOrder) {
        PurchaseOrder purchaseOrder = service.getPurchaseOrderById(id);
        if (purchaseOrder == null) {
            return Util.generateResponse("Purchase order " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateOrder, purchaseOrder);
        purchaseOrder.setId(id);
        return Util.generateResponse("successfully Updated Purchase Order!", HttpStatus.OK,
                service.updatePurchaseOrder(purchaseOrder));
    }

    @PutMapping
    public ResponseEntity<Object> updateCahBoxList(@RequestParam("id") long id, @RequestBody List<CashBox> addCashBoxList) {
        PurchaseOrder purchaseOrder = service.getPurchaseOrderById(id);
        if (purchaseOrder == null) {
            return Util.generateResponse("Customer order " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        List<CashBox> cashBoxList = purchaseOrder.getCashBoxList();
        for(CashBox cashBox : addCashBoxList) {
            cashBoxList.add(cashBox);
        }
        purchaseOrder.setCashBoxList(cashBoxList);
        service.updatePurchaseOrder(purchaseOrder);
        return Util.generateResponse("successfully Updated Purchase Order!", HttpStatus.OK,
                service.updatePurchaseOrder(purchaseOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePurchaseOrder(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = service.getPurchaseOrderById(id);
        if (purchaseOrder == null) {
            return Util.generateResponse("Purchase Order " + id + " was not found", HttpStatus.BAD_REQUEST, null);
        }
        purchaseOrder.setStatus("cancelled");
        service.updatePurchaseOrder(purchaseOrder);
        return Util.generateResponse("Successfully Deleted Purchase Order!", HttpStatus.OK, id);
    }
}
