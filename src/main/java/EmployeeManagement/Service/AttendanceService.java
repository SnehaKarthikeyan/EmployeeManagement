package com.inzeph.EmployeeManagement.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Attendance;
import com.inzeph.EmployeeManagement.Repository.AttendanceRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.AttendanceServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AttendanceService implements AttendanceServiceInterface {
    Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    AttendanceRepo repo;

    @Override
    public Attendance getByEmpId(long empId) {
        return repo.findByEmpId(empId);
    }

    @Override
    public List<Attendance> getByDate(LocalDate date) {
        return repo.findByDate(date);
    }

    @Override
    public Attendance addAttendance(Attendance attendance) {
        return repo.save(attendance);
    }

    @Override
    public List<Attendance> getByIdAndDate(long id, LocalDate date) {
        return repo.findByEmpIdAndDate(id, date);
    }

    @Override
    public Attendance getById(long id) {
        Optional<Attendance> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
}
