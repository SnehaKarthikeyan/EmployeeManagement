package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Product;

public interface ProductServiceInterface {
    List<Product> getAllProducts();
    Product getByCode(String code);
    Product addProduct(Product product);
    void deleteProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getByName(String name);
    Product getById(long id);
}
