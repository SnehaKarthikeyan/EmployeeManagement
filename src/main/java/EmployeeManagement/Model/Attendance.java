package com.inzeph.EmployeeManagement.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ATTENDANCE")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long empId;
    private LocalDate date;
    private LocalTime login;
    private LocalTime logout;
    private String location;
    private boolean isIncharge;
}
