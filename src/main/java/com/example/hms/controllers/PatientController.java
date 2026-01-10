package com.example.hms.controllers;

import com.example.hms.models.Patient;
import com.example.hms.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        System.out.println("Fetching all patients");
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        System.out.println("Creating new patient");
        return patientService.createPatient(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        System.out.println("Fetching patient by id: " + id);
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        System.out.println("Deleting patient by id: " + id);
        patientService.deletePatient(id);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        System.out.println("Updating patient");
        return patientService.updatePatient(patient);
    }

    @GetMapping("/gender/{gender}")
    public List<Patient> getPatientsByGender(@PathVariable String gender) {
        System.out.println("Fetching patients by gender: " + gender);
        return null;
    }
}