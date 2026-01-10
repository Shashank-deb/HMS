package com.example.hms.controllers;

import com.example.hms.models.Bill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @GetMapping
    public List<Bill> getAllBills() {
        System.out.println("Fetching all bills");
        return null;
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        System.out.println("Creating new bill");
        return bill;
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        System.out.println("Fetching bill by id: " + id);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBillById(@PathVariable Long id) {
        System.out.println("Deleting bill by id: " + id);
    }

    @PutMapping
    public Bill updateBill(@RequestBody Bill bill) {
        System.out.println("Updating bill");
        return bill;
    }

    @GetMapping("/patient/{patientId}")
    public List<Bill> getBillsByPatientId(@PathVariable Long patientId) {
        System.out.println("Fetching bills for patient id: " + patientId);
        return null;
    }

    @GetMapping("/status/{status}")
    public List<Bill> getBillsByStatus(@PathVariable String status) {
        System.out.println("Fetching bills by status: " + status);
        return null;
    }
}