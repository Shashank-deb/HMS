package com.example.hms.repository;
import com.example.hms.models.Webhook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WebhookRepository extends JpaRepository<Webhook, Long> {
    List<Webhook> findByEventType(String eventType);
}