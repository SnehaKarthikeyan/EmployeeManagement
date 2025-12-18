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
@Table(name = "CUSTOMER_ORDER_ITEMS")
public class CustomerOrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productCode;
    private double productQuantity;
    private String productUnit;
}
