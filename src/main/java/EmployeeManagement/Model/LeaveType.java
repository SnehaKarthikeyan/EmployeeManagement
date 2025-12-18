package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE_LEAVE_TYPE")
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String leaveType;
}