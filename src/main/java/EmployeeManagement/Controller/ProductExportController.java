package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.ProductExportServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Product;
import com.inzeph.EmployeeManagement.Model.ProductExport;
import com.inzeph.EmployeeManagement.Model.ProductImportExportDetails;
import com.inzeph.EmployeeManagement.Model.ProductQuantity;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/export")
public class ProductExportController {
    Logger logger = LoggerFactory.getLogger(ProductExportController.class);

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    ProductExportServiceInterface service;

    @GetMapping
    public ResponseEntity<Object> getProductExport() {
        List<ProductExport> productExport = service.getAllProductExports();
        if (productExport.isEmpty()) {
            return Util.generateResponse("Product export was not Found", HttpStatus.NO_CONTENT, null);
        }
        return Util.generateResponse("Successfully retrieved product export(s)", HttpStatus.OK, productExport);
    }

    @PostMapping
    public ResponseEntity<Object> createProductExport(@RequestBody ProductExport productExport) {
        Product product;
        ProductQuantity productQuantity;
        List<ProductImportExportDetails> productImportExportDetails = productExport.getProducts();
        for (ProductImportExportDetails productImportExportDetail : productImportExportDetails) {
            product = productService.getById(productImportExportDetail.getProductId());
            productQuantity = product.getProductQuantity();
            productQuantity.setQuantity(productQuantity.getQuantity() - productImportExportDetail.getQuantity());
            product.setProductQuantity(productQuantity);
            productService.addProduct(product);
        }
        return Util.generateResponse("Successfully added export details " + productExport.getId(), HttpStatus.CREATED,
                service.addProductExport(productExport));
    }
}
