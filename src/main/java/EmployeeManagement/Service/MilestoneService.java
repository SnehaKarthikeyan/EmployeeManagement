package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Milestone;
import com.inzeph.EmployeeManagement.Repository.EmployeeRepo;
import com.inzeph.EmployeeManagement.Repository.MilestoneRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.MilestoneServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService implements MilestoneServiceInterface {
    Logger logger = LoggerFactory.getLogger(MilestoneServiceInterface.class);

    @Autowired
    MilestoneRepo repo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public List<Milestone> getAllMilestones() {
        return repo.findAll();
    }

    @Override
    public Milestone getById(Long id) {
        Optional<Milestone> milestone = repo.findById(id);
        return milestone.isPresent() ? milestone.get() : null;
    }

    @Override
    public Milestone getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Milestone saveMilestone(Milestone milestone){ return repo.save(milestone); }

    @Override
    public Milestone updateMilestone(Milestone milestone) {
        return repo.save(milestone);
    }

    @Override
    public void deleteMilestone(Milestone milestone) {
        repo.delete(milestone);
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
