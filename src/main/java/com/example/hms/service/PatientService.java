package com.example.hms.service;

import com.example.hms.models.Patient;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PatientService {
    public List<Patient> getAllPatients() {
        try {
            System.out.println("into service layer");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Patient getPatientById(Long id) {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
