package com.inzeph.EmployeeManagement.Service;

import com.inzeph.EmployeeManagement.Repository.BillRepo;
import com.inzeph.EmployeeManagement.ServiceInterface.BillServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.inzeph.EmployeeManagement.Model.Bill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BillService implements BillServiceInterface {
    Logger logger = LoggerFactory.getLogger(BillService.class);

    @Autowired
    BillRepo repo;

    @Override
    public Bill addBill(Bill bill) {
        return repo.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return repo.findAll();
    }

    @Override
    public Bill getById(Long id) {
        Optional<Bill> e = repo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    @Override
    public Bill updateBill(Bill bill) {
        return repo.save(bill);
    }

    @Override
    public void deleteBill(Bill bill) {
        repo.delete(bill);
    }
}
