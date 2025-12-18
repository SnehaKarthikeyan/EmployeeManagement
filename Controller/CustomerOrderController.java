package com.inzeph.EmployeeManagement.Controller;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.*;
import com.inzeph.EmployeeManagement.Utils.Util;

import com.inzeph.EmployeeManagement.ServiceInterface.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController {
    Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);

    @Autowired
    CustomerOrderServiceInterface service;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    CartServiceInterface cartService;

    @Autowired
    CustomerServiceInterface customerService;

    @Autowired
    ClientServiceInterface clientService;

    @GetMapping
    public ResponseEntity<Object> getAllCustomerOrder() {
        List<CustomerOrder> customerOrders = service.getAllCustomerOrder();
        if (customerOrders.isEmpty()) {
            return Util.generateResponse("Customer Order was not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved Customer Order!", HttpStatus.OK, customerOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerOrderById(@PathVariable long id) {
        CustomerOrder customerOrder = service.getCustomerOrderById(id);
        if (customerOrder == null) {
            return Util.generateResponse("Customer Order " + id + " not found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved Customer Order!", HttpStatus.OK, customerOrder);
    }

    @PostMapping
    public ResponseEntity<Object> createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        List<CustomerOrderItems> productList = customerOrder.getProductList();
        Product product;
        ProductQuantity productQuantity;
        if(customerService.getCustomerById(customerOrder.getCustomer()) == null) {
            return Util.generateResponse("Customer " + customerOrder.getCustomer() + " not found", HttpStatus.NO_CONTENT, null);
        }
        else if(clientService.getClientById(customerOrder.getParty()) == null) {
            return Util.generateResponse("Client " + customerOrder.getParty() + " not found", HttpStatus.NO_CONTENT, null);
        }
        for(CustomerOrderItems customerOrderItem : productList) {
            product = productService.getByCode(customerOrderItem.getProductCode());
            productQuantity = product.getProductQuantity();
            if(productQuantity.getQuantity() >= customerOrderItem.getProductQuantity()) {
                productQuantity.setQuantity(productQuantity.getQuantity() - customerOrderItem.getProductQuantity());
            }
            else {
                Cart cart = new Cart();
                cart.setProduct(product.getId());
                cart.setQuantity(customerOrderItem.getProductQuantity() - productQuantity.getQuantity());
                cartService.addCart(cart);
                productQuantity.setQuantity(0);
            }
            product.setProductQuantity(productQuantity);
            productService.updateProduct(product);
        }
        return Util.generateResponse("Successfully created an order", HttpStatus.CREATED,
                service.addCustomerOrder(customerOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomerOrder(@PathVariable long id, @RequestBody CustomerOrder updateOrder) {
        CustomerOrder customerOrder = service.getCustomerOrderById(id);
        if (customerOrder == null) {
            return Util.generateResponse("Customer order " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        BeanUtils.copyProperties(updateOrder, customerOrder);
        customerOrder.setId(id);
        return Util.generateResponse("successfully Updated Customer Order!", HttpStatus.OK,
                service.updateCustomerOrder(customerOrder));
    }

    @PutMapping
    public ResponseEntity<Object> updateCahBoxList(@RequestParam("id") long id, @RequestBody List<CashBox> addCashBoxList) {
        CustomerOrder customerOrder = service.getCustomerOrderById(id);
        if (customerOrder == null) {
            return Util.generateResponse("Customer order " + id + " was not found ", HttpStatus.NOT_FOUND, null);
        }
        List<CashBox> cashBoxList = customerOrder.getCashBoxList();
        for(CashBox cashBox : addCashBoxList) {
            cashBoxList.add(cashBox);
        }
        customerOrder.setCashBoxList(cashBoxList);
        service.updateCustomerOrder(customerOrder);
        return Util.generateResponse("successfully Updated Customer Order!", HttpStatus.OK,
                service.updateCustomerOrder(customerOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerOrder(@PathVariable Long id) {
        CustomerOrder customerOrder = service.getCustomerOrderById(id);
        if (customerOrder == null) {
            return Util.generateResponse("Customer Order " + id + " was not found", HttpStatus.BAD_REQUEST, null);
        }
        customerOrder.setStatus("cancelled");
        service.updateCustomerOrder(customerOrder);
        return Util.generateResponse("Successfully Deleted!", HttpStatus.OK, id);
    }
}
