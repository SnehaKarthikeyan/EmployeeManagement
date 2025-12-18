package com.inzeph.EmployeeManagement.Repository;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByisActive(boolean active);
    boolean existsByAadhar(Long aadhar);
    boolean existsByContact(Long contact);
    Employee findByContact(Long contact);
}
