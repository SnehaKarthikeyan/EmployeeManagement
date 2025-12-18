package com.inzeph.EmployeeManagement.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.EmployeeLeaveCount;
import com.inzeph.EmployeeManagement.Model.Role;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeServiceInterface service;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping
	public ResponseEntity<Object> getEmployee() throws Exception {
		logger.info(" log example employeeController");
		List<Employee> employees = service.getAllEmployees();
		if (employees.isEmpty()) {
			return Util.generateResponse("Employee was not Found", HttpStatus.NO_CONTENT, null);
		}
		ArrayList<Object> json = new ArrayList<>();
		for (Employee employee : employees) {
			Role role = service.getByRoleId(employee.getRole());
			json.add(Util.convertJSON(employee, role, "role"));
		}
		return Util.generateResponse("Successfully retrieved Employee(s) details", HttpStatus.OK, json);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByEmployeeId(@PathVariable long id) throws Exception {
		Employee employee = service.getById(id);
		if (employee == null || !employee.isActive()) {
			return Util.generateResponse("Employee " + id + " was Not Found", HttpStatus.NOT_FOUND, null);
		}
		Role role = service.getByRoleId(employee.getRole());
		return Util.generateResponse("Successfully retrieved Employee " + id + " details", HttpStatus.OK,
				Util.convertJSON(employee, role, "role"));
	}

	@PostMapping
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) throws Exception {
		if (service.getByField("aadhar", employee.getAadhar())) {
			return Util.generateResponse("Aadhar Number " + employee.getAadhar() + " already exists",
					HttpStatus.BAD_REQUEST, null);
		} else if (service.getByField("contact", employee.getContact())) {
			return Util.generateResponse("Phone Number " + employee.getContact() + " already exists",
					HttpStatus.BAD_REQUEST, null);
		} else if (service.getByField("role", employee.getRole())) {
			return Util.generateResponse("Role " + employee.getRole() + " was Not Found", HttpStatus.BAD_REQUEST, null);
		}
		if (employee.getEmployeeLeaveCount() != null) {
			employee.setEmployeeLeaveCount(new EmployeeLeaveCount(String.valueOf(LocalDate.now().getYear()), 10, 10));
		}
		employee.setPassword(encoder().encode((String.valueOf(employee.getPassword()))));
		Employee response = service.addEmployee(employee);
		Role role = service.getByRoleId(response.getRole());
		return Util.generateResponse("Successfully added Employee " + response.getId() + " details", HttpStatus.CREATED,
				Util.convertJSON(response, role, "role"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody Employee employee)
			throws Exception {
		Employee emp = service.getById(id);
		if (emp == null || !emp.isActive()) {
			return Util.generateResponse("Employee " + id + " was not found", HttpStatus.NOT_FOUND, null);
		} else if (service.getByField("aadhar", employee.getAadhar())) {
			return Util.generateResponse("Aadhar Number " + employee.getAadhar() + " already exists",
					HttpStatus.BAD_REQUEST, null);
		} else if (service.getByField("contact", employee.getContact())) {
			return Util.generateResponse("Phone Number " + employee.getContact() + " already exists",
					HttpStatus.BAD_REQUEST, null);
		} else if (service.getByField("role", employee.getRole())) {
			return Util.generateResponse("Role " + employee.getRole() + " was Not Found",
					HttpStatus.BAD_REQUEST, null);
		}
		emp.setEmployeeLeaveCount(employee.getEmployeeLeaveCount());
		emp.setPassword(employee.getPassword());
		BeanUtils.copyProperties(employee, emp);
		emp.setId(id);
		Role role = service.getByRoleId(emp.getRole());
		return Util.generateResponse("Successfully updated Employee " + id + " details", HttpStatus.OK,
				Util.convertJSON(service.updateEmployee(emp), role, "role"));
	}

	@PutMapping("/{id}/token")
	public ResponseEntity<Object> updateEmployeeToken(@PathVariable long id, @RequestBody Employee employee)
			throws Exception {
		Employee emp = service.getById(id);
		if (emp == null || !emp.isActive()) {
			return Util.generateResponse("Employee " + id + " was not found", HttpStatus.NOT_FOUND, null);
		}
		emp.setToken(employee.getToken());
		Role role = service.getByRoleId(emp.getRole());
		return Util.generateResponse("Successfully updated Employee " + id + " Token", HttpStatus.OK,
				Util.convertJSON(service.updateEmployee(emp), role, "role"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable long id) {
		Employee employee = service.getById(id);
		if (employee == null || !employee.isActive()) {
			return Util.generateResponse("Employee " + id + " was not found", HttpStatus.BAD_REQUEST, null);
		}
		employee.setActive(false);
		service.updateEmployee(employee);
		return Util.generateResponse("Successfully deactivated Employee " + id + " details", HttpStatus.OK, id);
	}

}
