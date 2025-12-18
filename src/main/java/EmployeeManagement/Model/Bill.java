package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double cgst;
    private double sgst;
    private double discount;

    private double total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BillItems", referencedColumnName = "id")
    private List<BillItems> billItems;

    private String seller;
    private long employee;

    @Builder.Default
    private Date created_time = new Date(System.currentTimeMillis());
}
