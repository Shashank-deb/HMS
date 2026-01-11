package com.example.hms.events;
import com.example.hms.models.Appointment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AppointmentEvent extends ApplicationEvent {
    private final Appointment appointment;
    public AppointmentEvent(Object source, Appointment appointment) {
        super(source);
        this.appointment = appointment;
    }
}