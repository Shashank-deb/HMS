package com.example.hms.service;

import com.example.hms.models.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatient(Long id);
    List<Patient> getPatientsByGender(String gender);
}