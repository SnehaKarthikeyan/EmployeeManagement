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
@Table(name = "PRODUCT_IMPORT")
public class ProductImport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String invoiceNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductImportDetails", referencedColumnName = "id")
    private List<ProductImportExportDetails> products;

    private long employeeId;

    private String driverName;
    private String vehicleNumber;
    private String driverComments;

    private boolean isBillPaid;
    private String description;

    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());
}
