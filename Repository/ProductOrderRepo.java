package com.inzeph.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.ProductOrder;

@Repository
public interface ProductOrderRepo extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByName(String name);
}
