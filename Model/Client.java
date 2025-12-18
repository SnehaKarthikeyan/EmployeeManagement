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
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String companyName;
    private String companyAddress;
    private long companyNumber;
    private String website;

    private String person1;
    private String phoneNumber1;
    private String email1;

    private String person2;
    private String phoneNumber2;
    private String email2;
}
