package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.ProductImportRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductImportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductImport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductImportService implements ProductImportServiceInterface {
    Logger logger = LoggerFactory.getLogger(ProductImportService.class);

    @Autowired
    ProductImportRepo repo;

    @Override
    public ProductImport addProductImport(ProductImport productImport) {
        return repo.save(productImport);
    }

    @Override
    public List<ProductImport> getAllProductImports() {
        return repo.findAll();
    }
}
