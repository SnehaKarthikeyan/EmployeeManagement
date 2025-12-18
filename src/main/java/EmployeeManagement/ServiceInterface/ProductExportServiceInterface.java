package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductExport;

public interface ProductExportServiceInterface {
    List<ProductExport> getAllProductExports();
    ProductExport addProductExport(ProductExport productExport);
}
