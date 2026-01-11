package com.example.hms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class DoctorScheduleEngine {

    private final StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "doctor_schedule:";

    public boolean isSlotAvailable(Long doctorId, LocalDateTime time) {
        String key = KEY_PREFIX + doctorId;

        double appointmentTime = time.toEpochSecond(ZoneOffset.UTC);
        double startRange = appointmentTime - (29 * 60);
        double endRange = appointmentTime + (29 * 60);

        Long count = redisTemplate.opsForZSet().count(key, startRange, endRange);

        return count == null || count == 0;
    }

    public void bookSlot(Long doctorId, LocalDateTime time) {
        String key = KEY_PREFIX + doctorId;
        double score = time.toEpochSecond(ZoneOffset.UTC);
        redisTemplate.opsForZSet().add(key, time.toString(), score);
    }
}