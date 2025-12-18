package com.inzeph.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByProduct(long productId);
}
