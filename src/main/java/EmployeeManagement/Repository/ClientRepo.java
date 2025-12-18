package com.inzeph.EmployeeManagement.Repository;

import com.inzeph.EmployeeManagement.Model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
