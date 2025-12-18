package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Leave;
import com.inzeph.EmployeeManagement.Model.LeaveType;

public interface LeaveServiceInterface {
    List<Leave> getLeaveByEmpId(long id);
    Leave getById(long id);
    Leave addLeave(Leave employee);
    LeaveType getByLeaveType(long leaveType);
}
