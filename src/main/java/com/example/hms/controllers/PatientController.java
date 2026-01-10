package com.example.hms.controllers;

import com.example.hms.models.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @GetMapping
    public List<Patient> getAllPatients() {
        System.out.println("Fetching all patients");
        return null;
    }

    @PostMapping
    public Patient createPatient(Patient patient) {
        System.out.println("Creating new patient");
        return patient;
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        System.out.println("Fetching patient by id");
        return null;
    }


    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        System.out.println("Deleting patient by id");
    }

    @PutMapping
    public Patient updatePatient(Patient patient) {
        System.out.println("Updating patient");
        return patient;
    }


}
