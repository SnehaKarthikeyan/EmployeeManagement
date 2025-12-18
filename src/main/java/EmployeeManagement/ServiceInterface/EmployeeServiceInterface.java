package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Role;

public interface EmployeeServiceInterface {
    List<Employee> getAllEmployees();
    Employee getById(long id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Boolean getByField(String field, long value);
    Role getByRoleId(long id);
}
