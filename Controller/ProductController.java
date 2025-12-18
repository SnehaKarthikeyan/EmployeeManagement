package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.ProductServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Product;
import com.inzeph.EmployeeManagement.Model.ProductQuantity;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getProduct(@RequestParam(required = false) String name) {
        List<Product> product;
        if (name == null) {
            product = service.getAllProducts();
        } else {
            product = service.getByName(name);
        }
        if (product.isEmpty()) {
            return Util.generateResponse("Product was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved product(s)", HttpStatus.OK, product);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getByProductCode(@PathVariable("code") String code) {
        Product product = service.getByCode(code);
        if (product == null) {
            return Util.generateResponse("Product " + code + " was not Found", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved product " + code, HttpStatus.OK, product);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<Object> getLowWarningByCode(@PathVariable("code") String code) {
        Product product = service.getByCode(code);
        if (product == null) {
            return Util.generateResponse("Product " + code + " was not Found", HttpStatus.NOT_FOUND, null);
        }
        ProductQuantity productQuantity = product.getProductQuantity();
        if (product.getLowWarning() >= productQuantity.getQuantity()) {
            return Util.generateResponse("Product " + code + " stock was low", HttpStatus.NOT_FOUND, product);
        }
        return Util.generateResponse("Product " + code + " stock was in control", HttpStatus.OK, product);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return Util.generateResponse("Successfully added product " + product.getCode(), HttpStatus.CREATED,
                service.addProduct(product));
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateProduct(@PathVariable("code") String code, @RequestBody Product product) {
        Product updatedProduct = service.getByCode(code);
        if (updatedProduct == null) {
            return Util.generateResponse("Product " + code + " was not found", HttpStatus.NOT_FOUND, null);
        }
        product.setId(updatedProduct.getId());
        BeanUtils.copyProperties(product, updatedProduct);
        return Util.generateResponse("Successfully updated product " + code, HttpStatus.OK,
                service.updateProduct(updatedProduct));
    }

    @PutMapping
    public ResponseEntity<Object> updateProductQuantity(@RequestParam("code") String code, @RequestBody Product product) {
        Product updatedProduct = service.getByCode(code);
        if (updatedProduct == null) {
            return Util.generateResponse("Product " + code + " was not found", HttpStatus.NOT_FOUND, null);
        }
        ProductQuantity productQuantity = product.getProductQuantity();
        ProductQuantity updatedProductQuantity = updatedProduct.getProductQuantity();
        updatedProductQuantity.setQuantity(productQuantity.getQuantity());
        updatedProduct.setProductQuantity(updatedProductQuantity);
        return Util.generateResponse("Successfully updated product" + code + "quantity", HttpStatus.NOT_FOUND,
                    service.updateProduct(updatedProduct));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("code") String code) {
        Product product = service.getByCode(code);
        if (product == null) {
            return Util.generateResponse("Product " + code + " was not found", HttpStatus.NOT_FOUND, null);
        }
        service.deleteProduct(product);
        return Util.generateResponse("Successfully deleted product " + code, HttpStatus.OK, code);
    }
}
