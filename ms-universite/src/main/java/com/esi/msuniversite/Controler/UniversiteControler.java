package com.esi.msuniversite.Controler;

import com.esi.msuniversite.DTO.UniversiteDetailDTO;
import com.esi.msuniversite.Entities.Universite;
import com.esi.msuniversite.Repository.UniversityRepository;
import com.esi.msuniversite.Service.UniversiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/uni")
@RequiredArgsConstructor
public class UniversiteControler {

    private final UniversityRepository universityRepository;
    private final UniversiteService universiteService;

    @GetMapping("/universites")
    public List<Universite> getUniversites() {
        return universityRepository.findAll();
    }

    // New: with Recteur, Doyens, CircuitBreaker, and Caching
    @GetMapping("/details")
    public List<UniversiteDetailDTO> getUniversiteDetails() {
        return universiteService.getAllUniversiteDetails();
    }
}