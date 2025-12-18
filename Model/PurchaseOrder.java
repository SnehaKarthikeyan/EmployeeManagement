package com.inzeph.EmployeeManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderName;

    private long partyId;

    private String orderDate;
    private String deliveryDate;

    private long deliveryBy;
    private long billNo;

    private long party;

    private long customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cash_box_list", referencedColumnName = "id")
    private List<CashBox> cashBoxList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_order_items", referencedColumnName = "id")
    private List<CustomerOrderItems> productList;

    @Builder.Default
    private String status = "ordered";
}
