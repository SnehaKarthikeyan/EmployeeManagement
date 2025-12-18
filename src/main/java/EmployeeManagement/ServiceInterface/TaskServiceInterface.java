package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Employee;
import com.inzeph.EmployeeManagement.Model.Task;

public interface TaskServiceInterface {
    List<Task> getAllTask();

    Task getById(Long id);

    Task getByName(String name);

    Task addTasks(Task task);

    Task updateTask(Task task);

    void deleteTask(Task task);

    Employee getByEmpId(long id);

    Boolean getByField(String field, long value);
}
