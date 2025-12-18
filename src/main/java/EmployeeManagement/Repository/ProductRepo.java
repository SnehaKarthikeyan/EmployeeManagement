package com.inzeph.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    Optional<Product> findByCode(String code);
}
