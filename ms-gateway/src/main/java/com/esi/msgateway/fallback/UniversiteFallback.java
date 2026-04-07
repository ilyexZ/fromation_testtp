package com.esi.msgateway.fallback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversiteFallback {
    private Long id;
    private String nom;
    private String ville;
}