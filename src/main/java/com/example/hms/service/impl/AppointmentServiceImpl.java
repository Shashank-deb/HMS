package com.example.hms.service.impl;

import com.example.hms.events.AppointmentEvent;
import com.example.hms.models.*;
import com.example.hms.repository.*;
import com.example.hms.service.AppointmentService;
import com.example.hms.dto.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorScheduleEngine scheduleEngine;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public Appointment createAppointment(AppointmentRequest request) {
        // 1. Check Memory Cache (Heavy Collection Logic)
        if (!scheduleEngine.isTimeSlotTaken(request.getDoctorId(), request.getAppointmentTime())) {
            throw new RuntimeException("Doctor slot unavailable (Conflict Detected)");
        }

        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow();
        Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow();

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentTime(request.getAppointmentTime())
                .status("SCHEDULED")
                .build();

        Appointment saved = appointmentRepository.save(appointment);
        scheduleEngine.bookSlot(doctor.getId(), request.getAppointmentTime());
        
        // 2. Trigger Async Webhook
        eventPublisher.publishEvent(new AppointmentEvent(this, saved));
        
        return saved;
    }

    // Implementing interface methods required by context...
    @Override public List<Appointment> getAllAppointments() { return appointmentRepository.findAll(); }
    @Override public Appointment getAppointmentById(Long id) { return appointmentRepository.findById(id).orElse(null); }
    @Override public Appointment updateAppointment(Appointment appointment) { return appointmentRepository.save(appointment); }
    @Override public void deleteAppointment(Long id) { appointmentRepository.deleteById(id); }
    @Override public List<Appointment> getAppointmentsByPatientId(Long id) { return appointmentRepository.findByPatientId(id); }
    @Override public List<Appointment> getAppointmentsByDoctorId(Long id) { return appointmentRepository.findByDoctorId(id); }
    @Override public Appointment createAppointment(Appointment a) { return appointmentRepository.save(a); } // Overload
}