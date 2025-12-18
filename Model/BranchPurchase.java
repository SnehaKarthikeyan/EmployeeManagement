package com.inzeph.EmployeeManagement.Model;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BRANCH_TRANSACTION")
public class BranchPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long branchId;
    private long employee;
    private String reason;
    private double amount;

    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());
}
