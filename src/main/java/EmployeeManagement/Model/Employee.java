package com.inzeph.EmployeeManagement.Model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String designation;
    private String email;
    private Long contact;
    private Long aadhar;
    private String address;
    private String birthDate;
    private String bloodGroup;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Bank bankDetails;

    private String password;
    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());

    private String joiningDate;

    @Builder.Default
    private boolean isActive = true;

    private long role;

    @Builder.Default
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeLeaveCount", referencedColumnName = "id")
    private EmployeeLeaveCount employeeLeaveCount = new EmployeeLeaveCount(String.valueOf(LocalDate.now().getYear()),
            10, 10);

    private String token;
}