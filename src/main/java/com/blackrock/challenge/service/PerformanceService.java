package com.blackrock.challenge.service;
import com.blackrock.challenge.dto.response.PerformanceResponse;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class PerformanceService {

    private final MeterRegistry meterRegistry;

    public PerformanceService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public PerformanceResponse gatherMetrics(long requestStartTime) {

        String formattedTime;
        try {
            Timer httpTimer = meterRegistry.find("http.server.requests").timer();
            if (httpTimer != null && httpTimer.count() > 0) {
                double avgMs = httpTimer.mean(TimeUnit.MILLISECONDS);
                formattedTime = formatDurationMs((long) avgMs);
            } else {
                long elapsed = System.currentTimeMillis() - requestStartTime;
                formattedTime = formatDurationMs(elapsed);
            }
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - requestStartTime;
            formattedTime = formatDurationMs(elapsed);
        }

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        long heapUsed    = memoryMXBean.getHeapMemoryUsage().getUsed();
        long nonHeapUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        double totalMB   = (heapUsed + nonHeapUsed) / (1024.0 * 1024.0);
        String formattedMemory = String.format("%.2f MB", totalMB);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadMXBean.getThreadCount();

        return new PerformanceResponse(formattedTime, formattedMemory, threadCount);
    }

    private String formatDurationMs(long millis) {
        Duration duration = Duration.ofMillis(millis);
        long hours   = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long ms      = duration.toMillisPart();
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, ms);
    }
}