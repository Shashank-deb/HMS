package com.example.hms.service.impl;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Component
public class DoctorScheduleEngine {
    // Advanced Data Structure: ConcurrentSkipListMap for O(log n) time complexity range queries
    private final Map<Long, ConcurrentSkipListMap<LocalDateTime, Boolean>> doctorSchedules = new ConcurrentHashMap<>();

    public boolean isTimeSlotTaken(Long doctorId, LocalDateTime time) {
        return doctorSchedules.computeIfAbsent(doctorId, k -> new ConcurrentSkipListMap<>())
                .subMap(time.minusMinutes(29), time.plusMinutes(29)) // Check overlap
                .isEmpty();
    }

    public void bookSlot(Long doctorId, LocalDateTime time) {
        doctorSchedules.computeIfAbsent(doctorId, k -> new ConcurrentSkipListMap<>())
                .put(time, true);
    }
}