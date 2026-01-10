package com.example.hms.controllers;

import com.example.hms.models.Doctor;
import com.example.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {


    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    @PutMapping
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @GetMapping("/specialty/{specialty}")
    public List<Doctor> getDoctorsBySpecialty(@PathVariable String specialty) {
        return doctorService.getDoctorsBySpecialty(specialty);
    }
}