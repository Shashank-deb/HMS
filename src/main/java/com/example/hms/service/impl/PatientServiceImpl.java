package com.example.hms.service.impl;

import com.example.hms.models.Patient;
import com.example.hms.repository.PatientRepository;
import com.example.hms.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {


    private PatientRepository patientRepository;

    private static final Logger logger=LoggerFactory.getLogger(PatientServiceImpl.class);
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        if (patient.getId() != null && patientRepository.existsById(patient.getId())) {
            return patientRepository.save(patient);
        }
        return null;
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }
}