package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.LocationRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.LocationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzeph.EmployeeManagement.Model.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LocationService implements LocationServiceInterface {
    Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    LocationRepo repo;

    @Override
    public Location getByEmpId(Long empId) {
        return repo.findByEmpId(empId);
    }

    @Override
    public Location addLocation(Location location) {
        return repo.save(location);
    }

}
