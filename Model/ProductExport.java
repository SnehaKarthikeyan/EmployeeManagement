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
@Table(name = "PRODUCT_EXPORT")
public class ProductExport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductExportDetails", referencedColumnName = "id")
    private List<ProductImportExportDetails> products;

    private boolean isPaymentMade;
    private String deliveryChalanNumber;
    private String description;
    private String deliveredByEmployee;

    @Builder.Default
    private Date createdTime = new Date(System.currentTimeMillis());
}
