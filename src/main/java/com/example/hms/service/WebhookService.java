package com.example.hms.service;

import com.example.hms.events.AppointmentEvent;
import com.example.hms.repository.WebhookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WebhookService {
    private final WebhookRepository webhookRepository;
    private final WebClient webClient = WebClient.create();

    @Async
    @EventListener
    public void handleAppointmentEvent(AppointmentEvent event) {
        webhookRepository.findByEventType("APPOINTMENT_CREATED").parallelStream().forEach(hook -> {
            webClient.post().uri(hook.getUrl())
                    .bodyValue(event.getAppointment())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .subscribe();
        });
    }
}