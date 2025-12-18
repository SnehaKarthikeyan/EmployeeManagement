package com.inzeph.EmployeeManagement.Model;

import javax.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBERS")
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long projectId;
    private long employeeId;
}
