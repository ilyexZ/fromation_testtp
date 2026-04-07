package com.esi.msuniversite.Feign;

import com.esi.msuniversite.DTO.EnseignantDTO;
import com.esi.msuniversite.DTO.FormationDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-formation")
@LoadBalancerClient(name = "ms-formation")
public interface FormationFeignClient {

    @GetMapping("/api/ens/{id}")
    @CircuitBreaker(name = "enseignantCB", fallbackMethod = "fallbackEnseignant")
    @Cacheable(value = "enseignantCache", key = "#id")
    EnseignantDTO getEnseignantById(@PathVariable Long id);

    @GetMapping("/api/form/faculte/{faculteId}")
    @CircuitBreaker(name = "formationCB", fallbackMethod = "fallbackFormations")
    @Cacheable(value = "formationCache", key = "#faculteId")
    List<FormationDTO> getFormationsByFaculte(@PathVariable Long faculteId);

    default EnseignantDTO fallbackEnseignant(Long id, Throwable t) {
        EnseignantDTO fallback = new EnseignantDTO();
        fallback.setId(id);
        fallback.setNom("N/A (service indisponible)");
        fallback.setEmail("N/A");
        fallback.setGrade("N/A");
        return fallback;
    }

    default List<FormationDTO> fallbackFormations(Long faculteId, Throwable t) {
        return List.of();
    }
}