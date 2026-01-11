package com.example.hms.controllers;

import com.example.hms.models.Webhook;
import com.example.hms.repository.WebhookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookRepository webhookRepository;


    @PostMapping
    public ResponseEntity<Webhook> createWebhook(@RequestBody Webhook webhook) {
        return ResponseEntity.ok(webhookRepository.save(webhook));
    }


    @GetMapping
    public ResponseEntity<List<Webhook>> getAllWebhooks() {
        return ResponseEntity.ok(webhookRepository.findAll());
    }
}