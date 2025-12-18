package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    Task findByName(String name);
}
