package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE_LEAVE")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long employee;
    private long leaveType;
    private String startDate;
    private String endDate;
    private long noOfDays;
    private String reason;
    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());
}