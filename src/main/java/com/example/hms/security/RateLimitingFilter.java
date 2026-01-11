package com.example.hms.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RateLimitingFilter implements Filter {

    private final Map<String, RequestBucket> ipCache = new ConcurrentHashMap<>();
    private static final long TIME_WINDOW_MS = 60000; // 1 Minute
    private static final int MAX_REQUESTS = 100; // 100 req/min

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        String clientIp = request.getRemoteAddr();
        
        RequestBucket bucket = ipCache.compute(clientIp, (key, existing) -> {
            long now = System.currentTimeMillis();
            if (existing == null || now - existing.lastReset.get() > TIME_WINDOW_MS) {
                return new RequestBucket(now);
            }
            return existing;
        });

        if (bucket.count.incrementAndGet() > MAX_REQUESTS) {
            ((HttpServletResponse) res).setStatus(429);
            res.getWriter().write("{\"error\": \"Rate limit exceeded\"}");
            return;
        }

        chain.doFilter(req, res);
    }

    private static class RequestBucket {
        final AtomicLong lastReset;
        final AtomicInteger count = new AtomicInteger(0);

        RequestBucket(long timestamp) {
            this.lastReset = new AtomicLong(timestamp);
        }
    }
}