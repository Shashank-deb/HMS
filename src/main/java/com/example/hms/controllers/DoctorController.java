package com.example.hms.controllers;

import com.example.hms.models.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @GetMapping
    public List<Doctor> getAllDoctors() {
        System.out.println("Fetching all doctors");
        return null;
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        System.out.println("Creating new doctor");
        return doctor;
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        System.out.println("Fetching doctor by id: " + id);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id) {
        System.out.println("Deleting doctor by id: " + id);
    }

    @PutMapping
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        System.out.println("Updating doctor");
        return doctor;
    }

    @GetMapping("/specialty/{specialty}")
    public List<Doctor> getDoctorsBySpecialty(@PathVariable String specialty) {
        System.out.println("Fetching doctors by specialty: " + specialty);
        return null;
    }
}