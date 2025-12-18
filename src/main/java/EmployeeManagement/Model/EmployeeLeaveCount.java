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
@Table(name = "EMPLOYEE_LEAVE_COUNT")
public class EmployeeLeaveCount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String year;
    private long casualLeave;
    private long sickLeave;

    public EmployeeLeaveCount(String year,long casualLeave, long sickLeave){
        this.year = year;
        this.casualLeave = casualLeave;
        this.sickLeave = sickLeave;
    }
}
