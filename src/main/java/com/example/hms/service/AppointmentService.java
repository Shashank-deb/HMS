package com.example.hms.service;

import com.example.hms.dto.AppointmentRequest;
import com.example.hms.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    
    Appointment createAppointment(AppointmentRequest request);
    
    Appointment createAppointment(Appointment appointment);
    
    Appointment updateAppointment(Appointment appointment);
    void deleteAppointment(Long id);
    List<Appointment> getAppointmentsByPatientId(Long patientId);
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
}