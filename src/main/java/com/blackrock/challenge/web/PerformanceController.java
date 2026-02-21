package com.blackrock.challenge.web;

import com.blackrock.challenge.dto.response.PerformanceResponse;
import com.blackrock.challenge.service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performance")
    public ResponseEntity<PerformanceResponse> getPerformance() {
        long startTime = System.currentTimeMillis();
        PerformanceResponse response = performanceService.gatherMetrics(startTime);
        return ResponseEntity.ok(response);
    }
}
