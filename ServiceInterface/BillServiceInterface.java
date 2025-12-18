package com.inzeph.EmployeeManagement.ServiceInterface;

import java.util.List;

import com.inzeph.EmployeeManagement.Model.Bill;

public interface BillServiceInterface {
    List<Bill> getAllBills();
    Bill getById(Long id);
    Bill addBill(Bill bill);
    void deleteBill(Bill bill);
    Bill updateBill(Bill bill);
}
