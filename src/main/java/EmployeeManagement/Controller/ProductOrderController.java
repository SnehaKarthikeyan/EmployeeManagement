
package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.ProductOrderServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductOrder;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {
    Logger logger = LoggerFactory.getLogger(ProductOrderController.class);

    @Autowired
    ProductOrderServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getOrder(@RequestParam(required = false) String name) {
        List<ProductOrder> productOrders;
        if (name == null) {
            productOrders = service.getAllOrders();
        } else {
            productOrders = service.getByName(name);
        }
        if (productOrders.isEmpty()) {
            return Util.generateResponse("Product order was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved product order(s)", HttpStatus.OK, productOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByOrderId(@PathVariable("id") long id) {
        ProductOrder productOrder = service.getById(id);
        if (productOrder == null) {
            return Util.generateResponse("Product order " + id + " was Not Found", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved product order " + id, HttpStatus.OK, productOrder);
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody ProductOrder productOrder) {
        ProductOrder response = service.addOrder(productOrder);
        return Util.generateResponse("Successfully added product order " + response.getId(), HttpStatus.CREATED,
                response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable("id") Long id, @RequestBody ProductOrder productOrder) {
        ProductOrder updatedProductOrder = service.getById(id);
        if (updatedProductOrder == null) {
            return Util.generateResponse("Product order " + id + " was not found", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(productOrder, updatedProductOrder);
        updatedProductOrder.setId(id);
        return Util.generateResponse("Successfully updated product order " + id, HttpStatus.OK,
                service.updateOrder(updatedProductOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") long id) {
        ProductOrder productOrder = service.getById(id);
        if (productOrder == null) {
            return Util.generateResponse("Order " + id + " was not found", HttpStatus.NOT_FOUND, null);
        }
        service.deleteOrder(productOrder);
        return Util.generateResponse("Successfully deleted product order " + id, HttpStatus.OK, id);
    }
}
