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
@Table(name = "PRODUCT_IMPORT_EXPORT_DETAILS")
public class ProductImportExportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;

    private double quantity;
    private String unit;
}
