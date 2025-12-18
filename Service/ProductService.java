package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.ProductRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService implements ProductServiceInterface {
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepo repo;

    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getByCode(String code) {
        Optional<Product> e = repo.findByCode(code);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public Product getById(long id) {
        Optional<Product> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public void deleteProduct(Product product) {
        repo.delete(product);
    }
}
