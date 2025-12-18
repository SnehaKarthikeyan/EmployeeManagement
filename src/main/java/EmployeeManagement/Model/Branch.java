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
@Table(name = "BRANCH")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amountProvider;
    private long amountReceiver;
    private double amount;
    private String branchName;
    private String description;

    @Builder.Default
    private Date created_time = new Date(System.currentTimeMillis());
}
