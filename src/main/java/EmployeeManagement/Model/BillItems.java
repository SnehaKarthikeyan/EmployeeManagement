package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL_ITEMS")
public class BillItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productCode;
    private String productName;
    private String brand;
    private double buyRate;
    private LocalDate expiryDate;
    private String description;

    private double quantity;
    private String unit;
}
