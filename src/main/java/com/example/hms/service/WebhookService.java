package com.example.hms.service;

import com.example.hms.component.WebhookClient;
import com.example.hms.events.AppointmentEvent;
import com.example.hms.repository.WebhookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final WebhookRepository webhookRepository;
    private final WebhookClient webhookClient; // Inject the resilient client

    @Async
    @EventListener
    public void handleAppointmentEvent(AppointmentEvent event) {
        // For every registered webhook URL, try to send the data securely
        webhookRepository.findByEventType("APPOINTMENT_CREATED").parallelStream().forEach(hook -> {
            try {
                // The client handles Circuit Breaking and Retries internally
                webhookClient.sendPayload(hook.getUrl(), event.getAppointment());
            } catch (Exception e) {
                // Log only; don't crash the async thread
                System.err.println("Webhook failed after retries for " + hook.getUrl());
            }
        });
    }
}