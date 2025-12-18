package com.inzeph.EmployeeManagement.ServiceInterface;

import java.time.LocalDate;
import java.util.List;

import com.inzeph.EmployeeManagement.Model.Attendance;

public interface AttendanceServiceInterface {
    Attendance getById(long id);
    Attendance getByEmpId(long empId);
    List<Attendance> getByDate(LocalDate date);
    Attendance addAttendance(Attendance attendance);
    List<Attendance> getByIdAndDate(long id, LocalDate date);
}
