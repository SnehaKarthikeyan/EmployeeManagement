package com.inzeph.EmployeeManagement.Controller;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.PushNotificationRequest;
import com.inzeph.EmployeeManagement.Model.PushNotificationResponse;
import com.inzeph.EmployeeManagement.ServiceInterface.EmployeeServiceInterface;
import com.inzeph.EmployeeManagement.ServiceInterface.PushNotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class PushNotificationController {
    @Autowired
    private PushNotificationServiceInterface service;

    @Autowired
    private EmployeeServiceInterface employeeService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> sendTokenNotification(@PathVariable("id") long id, @RequestBody PushNotificationRequest request) {
        Employee employee = employeeService.getById(id);
        service.sendPushNotification(request, employee.getToken());
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}