package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.Model.*;
import com.inzeph.EmployeeManagement.Repository.LeaveRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.LeaveServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	Logger logger = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
    LeaveRepo repo;

	@Autowired
    LeaveServiceInterface service;

	@Autowired
    EmployeeServiceInterface employeeService;

	String[] strArray = { "bankDetails", "contact", "aadhar", "address", "birthDate", "bloodGroup", "password",
			"createdTime", "isActive", "joiningDate" };
	List<String> fields = Arrays.asList(strArray);

	@GetMapping("/{empId}")
	public ResponseEntity<Object> getLeaveDetailsByEmpId(@PathVariable("empId") long id) throws Exception {
		if (employeeService.getById(id) == null) {
			return Util.generateResponse("Employee " + id + " was not Found", HttpStatus.BAD_REQUEST, null);
		}
		List<Leave> leaves = service.getLeaveByEmpId(id);
		if (leaves.isEmpty()) {
			return Util.generateResponse("No leaves were taken by the Employee " + id, HttpStatus.NO_CONTENT, null);
		}
		ArrayList<Object> jsonResult = new ArrayList<>();
		Employee employee = employeeService.getById(id);
		Role role = employeeService.getByRoleId(employee.getRole());
		Object jsonEmployee = Util.removeJSONFields(employee, fields);
		jsonEmployee = Util.convertJSON(jsonEmployee, role, "role");
		Object jsonLeave;
		for (Leave leave : leaves) {
			LeaveType leaveType = service.getByLeaveType(leave.getLeaveType());
			jsonLeave = Util.convertJSON(leave, leaveType, "leaveType");
			jsonResult.add(Util.convertJSON(jsonLeave, jsonEmployee, "employee"));
		}
		return Util.generateResponse("Successfully retrieved leave(s) taken by the Employee " + id, HttpStatus.OK,
				jsonResult);
	}

	@GetMapping("/get/{empId}")
	public ResponseEntity<Object> getLeaveByEmpId(@PathVariable("empId") long id) throws Exception {
		if (employeeService.getById(id) == null) {
			return Util.generateResponse("Employee " + id + " was not Found", HttpStatus.BAD_REQUEST, null);
		}
		Employee employee = employeeService.getById(id);
		Role role = employeeService.getByRoleId(employee.getRole());
		Object jsonEmployee = Util.removeJSONFields(employee, fields);
		jsonEmployee = Util.convertJSON(jsonEmployee, role, "role");
		return Util.generateResponse("Successfully retrieved leave(s) taken by the Employee " + id, HttpStatus.OK,
				jsonEmployee);
	}

	@PostMapping
	public ResponseEntity<Object> createLeave(@RequestBody Leave leave) throws Exception {
		if (employeeService.getById(leave.getEmployee()) == null) {
			return Util.generateResponse("Employee " + leave.getEmployee() + " was not Found", HttpStatus.BAD_REQUEST,
					null);
		}
		Leave response = service.addLeave(leave);
		Employee employee = employeeService.getById(response.getEmployee());
		EmployeeLeaveCount employeeLeaveCount = employee.getEmployeeLeaveCount();
		LeaveType leaveType = service.getByLeaveType(response.getLeaveType());
		if (leaveType.getLeaveType().equals("SickLeave")) {
			employeeLeaveCount.setSickLeave(employeeLeaveCount.getSickLeave() - leave.getNoOfDays());
		} else if (leaveType.getLeaveType().equals("CasualLeave")) {
			employeeLeaveCount.setCasualLeave(employeeLeaveCount.getCasualLeave() - leave.getNoOfDays());
		}
		employee.setEmployeeLeaveCount(employeeLeaveCount);
		employeeService.updateEmployee(employee);
		Role role = employeeService.getByRoleId(employee.getRole());
		Object jsonEmployee = Util.convertJSON(employee, role, "role");
		Object jsonLeave = Util.convertJSON(response, leaveType, "leaveType");
		return Util.generateResponse("Successfully applied leave for the Employee " + response.getEmployee(),
				HttpStatus.CREATED,
				Util.convertJSON(jsonLeave, Util.removeJSONFields(jsonEmployee, fields), "employee"));
	}
}
