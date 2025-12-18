package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.LeaveRepo;
import com.inzeph.EmployeeManagement.Repository.LeaveTypeRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.LeaveServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Leave;
import com.inzeph.EmployeeManagement.Model.LeaveType;

@Service
public class LeaveService implements LeaveServiceInterface {
     Logger logger = LoggerFactory.getLogger(LeaveService.class);

     @Autowired
     LeaveRepo repo;

     @Autowired
     LeaveTypeRepo leaveTypeRepo;

     @Override
     public List<Leave> getLeaveByEmpId(long id) {
          return repo.findByEmployee(id);
     }

     @Override
     public Leave getById(long id) {
          Optional<Leave> e = repo.findById(id);
          return e.isPresent() ? e.get() : null;
     }

     @Override
     public Leave addLeave(Leave leave) {
          return repo.save(leave);
     }

     @Override
     public LeaveType getByLeaveType(long id) {
          Optional<LeaveType> e = leaveTypeRepo.findById(id);
          return e.isPresent() ? e.get() : null;
     }
}
