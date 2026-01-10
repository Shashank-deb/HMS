package com.example.hms.service.impl;

import com.example.hms.models.Bill;
import com.example.hms.repository.BillRepository;
import com.example.hms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {


    private BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Bill bill) {
        if (bill.getId() != null && billRepository.existsById(bill.getId())) {
            return billRepository.save(bill);
        }
        return null;
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<Bill> getBillsByPatientId(Long patientId) {
        return billRepository.findByPatientId(patientId);
    }

    @Override
    public List<Bill> getBillsByStatus(String status) {
        return billRepository.findByStatus(status);
    }
}