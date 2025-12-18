package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.ProductImport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepo extends JpaRepository<ProductImport, Long> {
}
