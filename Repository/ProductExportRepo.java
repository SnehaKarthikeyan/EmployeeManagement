package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.ProductExport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductExportRepo extends JpaRepository<ProductExport, Long> {
}
