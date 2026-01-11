package com.example.hms.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Webhook {
    @Id
    @GeneratedValue
    private Long id;
    private String url;
    private String eventType;
}