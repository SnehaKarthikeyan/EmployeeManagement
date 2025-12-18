package com.inzeph.EmployeeManagement.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.inzeph.EmployeeManagement.Model.Attendance;
import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.ServiceInterface.AttendanceServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import com.inzeph.EmployeeManagement.Utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    @Autowired
    AttendanceServiceInterface service;

    @Autowired
    EmployeeServiceInterface employeeService;

    @GetMapping("/{empId}")
    public ResponseEntity<Object> getAttendanceById(@PathVariable long empId) {
        Employee employee = employeeService.getById(empId);
        Attendance attendance = service.getByEmpId(empId);
        if (attendance == null || !employee.isActive()) {
            return Util.generateResponse("Employee " + empId + " was not found", HttpStatus.NOT_FOUND, null);
        }
        return Util.generateResponse("Successfully retrieved attendence for Employee " + empId, HttpStatus.OK,
                attendance);
    }

    @GetMapping
    public ResponseEntity<Object> getAttendanceByDate(@RequestParam String date) {
        if (date == null) {
            return Util.generateResponse("Date was Null", HttpStatus.NOT_FOUND, null);
        }
        LocalDate d = LocalDate.parse(date);
        return Util.generateResponse("Successfully retrieved attendence on date " + d, HttpStatus.OK,
                service.getByDate(d));
    }

    @PostMapping("/checkin/{empId}")
    public ResponseEntity<Object> checkedIn(@PathVariable Long empId, @RequestBody Attendance responseAttendance) {
        List<Attendance> attendance = service.getByIdAndDate(empId, LocalDate.now());
        for (Attendance at : attendance) {
            if (at.getLogout() == null) {
                return Util.generateResponse("Already Checked IN", HttpStatus.BAD_REQUEST, null);
            }
        }
        Attendance atd = new Attendance();
        atd.setEmpId(empId);
        atd.setLocation(responseAttendance.getLocation());
        atd.setDate(LocalDate.now());
        atd.setLogin(LocalTime.now());
        if (responseAttendance.isIncharge()) {
            atd.setIncharge(true);
        }
        service.addAttendance(atd);
        return Util.generateResponse("Checked IN Successfully at " + atd.getDate() + " " + atd.getLogin(),
                HttpStatus.OK, null);
    }

    @PostMapping("/checkout/{empId}")
    public ResponseEntity<Object> checkedOut(@PathVariable Long empId) {
        List<Attendance> attendance = service.getByIdAndDate(empId, LocalDate.now());
        long id = 0L;
        if (attendance != null) {
            for (Attendance at : attendance) {
                if (at.getLogout() == null) {
                    id = at.getId();
                }
            }
        }
        if (id == 0) {
            return Util.generateResponse("No Login detected to Logout. Kindly Login...!!", HttpStatus.BAD_REQUEST,
                    null);
        }
        Attendance atd = service.getById(id);
        atd.setLogout(LocalTime.now());
        service.addAttendance(atd);
        return Util.generateResponse("Checked out Successfully at " + atd.getDate() + " " + atd.getLogout(),
                HttpStatus.OK, null);
    }
}
