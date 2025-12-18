package com.inzeph.EmployeeManagement.Model;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long empId;
    private LocalDate date;
    private double latitude;
    private double longitude;
    private double accuracy;
    private double altitude;
}
