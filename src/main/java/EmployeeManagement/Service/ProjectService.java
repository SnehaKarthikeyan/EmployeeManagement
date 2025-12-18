package com.inzeph.EmployeeManagement.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Project;
import com.inzeph.EmployeeManagement.Repository.EmployeeRepo;
import com.inzeph.EmployeeManagement.Repository.ProjectRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.ProjectServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProjectService implements ProjectServiceInterface {
    Logger logger = LoggerFactory.getLogger(ProjectServiceInterface.class);

    @Autowired
    ProjectRepo repo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    @Override
    public Project getById(Long id) {
        Optional<Project> project = repo.findById(id);
        return project.isPresent() ? project.get() : null;
    }

    @Override
    public Project getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Project saveProject(Project project) {
        return repo.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return repo.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        repo.delete(project);
    }

    @Override
    public Employee getByEmpId(long id) {
        Optional<Employee> e = employeeRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public Boolean getByField(String field, long value) {
        if (field.equals("employee")) {
            Optional<Employee> e = employeeRepo.findById(value);
            return e.isEmpty();
        }
        return false;
    }

}
