package com.inzeph.EmployeeManagement.ServiceInterface;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Milestone;

import java.util.List;

public interface MilestoneServiceInterface {
    List<Milestone> getAllMilestones();

    Milestone getById(Long id);

    Milestone getByName(String name);

    Milestone saveMilestone(Milestone milestone);

    Milestone updateMilestone(Milestone milestone);

    void deleteMilestone(Milestone milestone);

    Employee getByEmpId(long id);

    Boolean getByField(String field, long value);
}
