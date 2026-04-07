package com.esi.msgateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CircuitBreakerController {

    @GetMapping("/defaultUniversite")
    public List<UniversiteFallback> universiteFallback() {
        return List.of(
                new UniversiteFallback(0L, "Service Indisponible", "N/A")
        );
    }

    @GetMapping("/defaultFormation")
    public List<String> formationFallback() {
        return List.of("Service Formation Indisponible");
    }
}