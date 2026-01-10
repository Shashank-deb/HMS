package com.example.hms.service;

import com.example.hms.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctor(Long id);
    List<Doctor> getDoctorsBySpecialty(String specialty);
}