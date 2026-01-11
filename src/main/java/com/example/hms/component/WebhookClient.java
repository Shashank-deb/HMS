package com.example.hms.component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class WebhookClient {

    private final WebClient webClient;

    public WebhookClient() {
        // [CRITICAL] Use System DNS Resolver to prevent "Cannot assign requested address" errors
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE);

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    /**
     * Sends data to an external URL.
     * Protected by Circuit Breaker "externalService".
     * If it fails, it Retries 3 times.
     * If it still fails (or Circuit is OPEN), it calls the fallback method.
     */
    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackSend")
    @Retry(name = "externalService")
    public void sendPayload(String url, Object payload) {
        try {
            webClient.post().uri(url)
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block(); // Block here to allow Resilience4j to track success/failure on this thread
            
            System.out.println("[SUCCESS] Webhook sent to " + url);
        } catch (Exception e) {
            // Re-throw to let CircuitBreaker count this as a failure
            throw new RuntimeException("Failed to call webhook: " + e.getMessage());
        }
    }

    // Fallback method must have the same signature + Exception param
    public void fallbackSend(String url, Object payload, Exception ex) {
        System.err.println("[FALLBACK] External system is down or busy. Circuit Breaker prevented call to: " + url);
        System.err.println("Reason: " + ex.getMessage());
        // In a real 35LPA system, you would push this to a "Dead Letter Queue" (Kafka/RabbitMQ) here.
    }
}