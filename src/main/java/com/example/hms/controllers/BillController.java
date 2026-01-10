package com.example.hms.controllers;

import com.example.hms.models.Bill;
import com.example.hms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {


    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBillById(@PathVariable Long id) {
        billService.deleteBill(id);
    }

    @PutMapping
    public Bill updateBill(@RequestBody Bill bill) {
        return billService.updateBill(bill);
    }

    @GetMapping("/patient/{patientId}")
    public List<Bill> getBillsByPatientId(@PathVariable Long patientId) {
        return billService.getBillsByPatientId(patientId);
    }

    @GetMapping("/status/{status}")
    public List<Bill> getBillsByStatus(@PathVariable String status) {
        return billService.getBillsByStatus(status);
    }
}