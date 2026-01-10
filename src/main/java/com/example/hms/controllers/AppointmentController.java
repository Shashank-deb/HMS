package com.example.hms.controllers;

import com.example.hms.models.Appointment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @GetMapping
    public List<Appointment> getAllAppointments() {
        System.out.println("Fetching all appointments");
        return null;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        System.out.println("Creating new appointment");
        return appointment;
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        System.out.println("Fetching appointment by id: " + id);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointmentById(@PathVariable Long id) {
        System.out.println("Deleting appointment by id: " + id);
    }

    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        System.out.println("Updating appointment");
        return appointment;
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId) {
        System.out.println("Fetching appointments for patient id: " + patientId);
        return null;
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        System.out.println("Fetching appointments for doctor id: " + doctorId);
        return null;
    }
}