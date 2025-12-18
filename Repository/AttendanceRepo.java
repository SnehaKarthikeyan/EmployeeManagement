package com.inzeph.EmployeeManagement.Repository;

import java.time.LocalDate;
import java.util.List;

import com.inzeph.EmployeeManagement.Model.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    List<Attendance> findByDate(LocalDate date);
    Attendance findByEmpId(Long empId);
    List<Attendance> findByEmpIdAndDate(Long empId, LocalDate date);
}
