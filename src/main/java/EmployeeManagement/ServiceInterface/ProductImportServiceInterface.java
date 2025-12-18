package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductImport;

public interface ProductImportServiceInterface {
    List<ProductImport> getAllProductImports();
    ProductImport addProductImport(ProductImport productImport);
}
