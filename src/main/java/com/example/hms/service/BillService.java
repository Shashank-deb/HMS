package com.example.hms.service;

import com.example.hms.models.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    Bill getBillById(Long id);
    Bill createBill(Bill bill);
    Bill updateBill(Bill bill);
    void deleteBill(Long id);
    List<Bill> getBillsByPatientId(Long patientId);
    List<Bill> getBillsByStatus(String status);
}