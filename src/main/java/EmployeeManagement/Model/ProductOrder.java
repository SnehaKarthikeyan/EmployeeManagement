package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    private String productCode;
    private String brand;
    private double buyRate;
    private double saleRate;
    private LocalDate expiryDate;
    private String description;

    private double quantity;
    private String unit;

    private String seller;

    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());
}
