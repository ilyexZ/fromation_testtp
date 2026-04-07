package com.esi.msuniversite.Service;

import com.esi.msuniversite.DTO.EnseignantDTO;
import com.esi.msuniversite.DTO.UniversiteDetailDTO;
import com.esi.msuniversite.Feign.FormationFeignClient;
import com.esi.msuniversite.Repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversiteService {

    private final UniversityRepository universityRepository;
    private final FormationFeignClient formationFeignClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Cacheable(value = "universiteDetails")
    public List<UniversiteDetailDTO> getAllUniversiteDetails() {
        CircuitBreaker cb = circuitBreakerFactory.create("enseignantService");

        return universityRepository.findAll().stream().map(u -> {
            UniversiteDetailDTO dto = new UniversiteDetailDTO();
            dto.setId(u.getId());
            dto.setNom(u.getNom());
            dto.setVille(u.getVille());

            // Fetch Recteur with CircuitBreaker
            EnseignantDTO recteur = cb.run(
                    () -> formationFeignClient.getEnseignantById(u.getRecteurId()),
                    throwable -> fallbackEnseignant(u.getRecteurId())
            );
            dto.setRecteur(recteur);

            // Fetch Doyen of each faculty with CircuitBreaker
            // u.getFacultes() works because facultes is EAGER in Universite
            List<EnseignantDTO> doyens = u.getFacultes().stream()
                    .map(f -> cb.run(
                            () -> formationFeignClient.getEnseignantById(f.getDoyenId()),
                            throwable -> fallbackEnseignant(f.getDoyenId())
                    ))
                    .collect(Collectors.toList());
            dto.setDoyens(doyens);

            return dto;
        }).collect(Collectors.toList());
    }

    // Fallback: returns an empty EnseignantDTO when ms-formation is unreachable
    private EnseignantDTO fallbackEnseignant(Long id) {
        EnseignantDTO fallback = new EnseignantDTO();
        fallback.setId(id);
        fallback.setNom("N/A (service unavailable)");
        fallback.setEmail("N/A");
        fallback.setGrade("N/A");
        return fallback;
    }
}