package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.ProductExportRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductExportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductExportService implements ProductExportServiceInterface {
    Logger logger = LoggerFactory.getLogger(ProductExportService.class);

    @Autowired
    ProductExportRepo repo;

    @Override
    public ProductExport addProductExport(ProductExport productExport) {
        return repo.save(productExport);
    }

    @Override
    public List<ProductExport> getAllProductExports() {
        return repo.findAll();
    }
}
