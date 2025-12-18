package com.inzeph.EmployeeManagement.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Role;
import com.inzeph.EmployeeManagement.Repository.EmployeeRepo;
import com.inzeph.EmployeeManagement.Repository.RoleRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService implements EmployeeServiceInterface {
     Logger logger = LoggerFactory.getLogger(EmployeeService.class);

     @Autowired
     EmployeeRepo repo;

     @Autowired
     RoleRepo roleRepo;

     @Override
     public List<Employee> getAllEmployees() {
          return repo.findByisActive(true);
     }

     @Override
     public Role getByRoleId(long id) {
          Optional<Role> e = roleRepo.findById(id);
          return e.isPresent() ? e.get() : null;
     }

     @Override
     public Employee getById(long id) {
          Optional<Employee> e = repo.findById(id);
          return e.isPresent() ? e.get() : null;
     }

     @Override
     public Employee addEmployee(Employee employee) {
          return repo.save(employee);
     }

     @Override
     public Employee updateEmployee(Employee employee) {
          return repo.save(employee);
     }

     @Override
     public Boolean getByField(String field, long value) {
          if (field.equals("role")) {
               Optional<Role> a = roleRepo.findById(value);
               return a.isEmpty();
          } else if (field.equals("aadhar")) {
               return repo.existsByAadhar(value);
          } else if (field.equals("contact")) {
               return repo.existsByContact(value);
          }
          return false;
     }
}
