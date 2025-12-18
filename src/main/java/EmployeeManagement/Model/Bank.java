package com.inzeph.EmployeeManagement.Model;

import javax.persistence.*;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BANK")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accountNumber;
    private String bankName;
    private String accountHolderName;
    private String ifsc;
    private String branch;
}
