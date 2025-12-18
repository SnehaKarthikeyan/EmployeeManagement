package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.ProductImportServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Product;
import com.inzeph.EmployeeManagement.Model.ProductImport;
import com.inzeph.EmployeeManagement.Model.ProductImportExportDetails;
import com.inzeph.EmployeeManagement.Model.ProductQuantity;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/import")
public class ProductImportController {
    Logger logger = LoggerFactory.getLogger(ProductImportController.class);

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    ProductImportServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getProductImport() {
        List<ProductImport> productImport = service.getAllProductImports();
        if (productImport.isEmpty()) {
            return Util.generateResponse("Product import was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved product import(s)", HttpStatus.OK, productImport);
    }

    @PostMapping
    public ResponseEntity<Object> createProductImport(@RequestBody ProductImport productImport) {
        Product product;
        ProductQuantity productQuantity;
        List<ProductImportExportDetails> productImportDetails = productImport.getProducts();
        for (ProductImportExportDetails productImportDetail : productImportDetails) {
            product = productService.getById(productImportDetail.getProductId());
            productQuantity = product.getProductQuantity();
            productQuantity.setQuantity(productQuantity.getQuantity() + productImportDetail.getQuantity());
            product.setProductQuantity(productQuantity);
            productService.addProduct(product);
        }
        return Util.generateResponse("Successfully added import details " + productImport.getId(), HttpStatus.CREATED,
                service.addProductImport(productImport));
    }
}
