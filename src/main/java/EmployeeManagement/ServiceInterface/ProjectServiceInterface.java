package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Project;

public interface ProjectServiceInterface {
    List<Project> getAllProjects();

    Project getById(Long id);

    Project getByName(String name);

    Project saveProject(Project project);

    Project updateProject(Project project);

    void deleteProject(Project project);

    Employee getByEmpId(long id);

    Boolean getByField(String field, long value);
}
