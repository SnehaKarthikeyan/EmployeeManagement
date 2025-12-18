package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.LocationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Location;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/location")
public class LocationController {
    Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    LocationServiceInterface service;

    @Autowired
    EmployeeServiceInterface employeeService;

    @GetMapping("/{empid}")
    public ResponseEntity<Object> getLocation(@PathVariable Long empid) throws Exception {
        Employee employee = employeeService.getById(empid);
        Location location = service.getByEmpId(empid);
        if (location == null || !employee.isActive()) {
            return Util.generateResponse("Employee " + empid + " was not found", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved location of Employee " + empid, HttpStatus.OK,
                Util.convertJSON(location, employee, "employee"));
    }

    @PostMapping
    public ResponseEntity<Object> createLocation(@RequestBody Location location) {
        location.setDate(LocalDate.now());
        return Util.generateResponse("Successfully added location ", HttpStatus.CREATED, service.addLocation(location));
    }

}
