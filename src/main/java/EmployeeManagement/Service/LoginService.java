package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.EmployeeRepo;
import com.inzeph.EmployeeManagement.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.toLowerCase().equals("admin")){
            username = "1234567890";
        }
        Employee user = employeeRepo.findByContact(Long.parseLong(username));
        if (user == null) {
            throw new UsernameNotFoundException("Enter a valid Username");
        }
        return new Login(user, roleRepo.findByid(user.getRole()));
    }

}
