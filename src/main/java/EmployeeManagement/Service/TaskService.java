package com.inzeph.EmployeeManagement.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Task;
import com.inzeph.EmployeeManagement.Repository.EmployeeRepo;
import com.inzeph.EmployeeManagement.Repository.TaskRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.TaskServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService implements TaskServiceInterface {
    Logger logger = LoggerFactory.getLogger(TaskServiceInterface.class);

    @Autowired
    TaskRepo repo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public List<Task> getAllTask() {
        return repo.findAll();
    }

    @Override
    public Task getById(Long id) {
        Optional<Task> task = repo.findById(id);
        return task.isPresent() ? task.get() : null;
    }

    @Override
    public Task getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Task addTasks(Task task) {
        return repo.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return repo.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        repo.delete(task);
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
