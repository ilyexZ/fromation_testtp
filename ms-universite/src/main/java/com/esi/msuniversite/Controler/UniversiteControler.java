package com.esi.msuniversite.Controler;

import com.esi.msuniversite.DTO.EnseignantDTO;
import com.esi.msuniversite.DTO.UniversiteDetailDTO;
import com.esi.msuniversite.Entities.Universite;
import com.esi.msuniversite.Feign.FormationFeignClient;
import com.esi.msuniversite.Repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/uni")
@RequiredArgsConstructor
public class UniversiteControler {

    private final UniversityRepository universityRepository;
    private final FormationFeignClient formationFeignClient;

    @GetMapping("/universites")
    public List<Universite> getUniversites() {
        return universityRepository.findAll();
    }

    @GetMapping("/details")
    public List<UniversiteDetailDTO> getUniversiteDetails() {
        return universityRepository.findAll().stream().map(u -> {
            UniversiteDetailDTO dto = new UniversiteDetailDTO();
            dto.setId(u.getId());
            dto.setNom(u.getNom());
            dto.setVille(u.getVille());

            dto.setRecteur(formationFeignClient.getEnseignantById(u.getRecteurId()));

            List<EnseignantDTO> doyens = u.getFacultes().stream()
                    .map(f -> formationFeignClient.getEnseignantById(f.getDoyenId()))
                    .collect(Collectors.toList());
            dto.setDoyens(doyens);

            return dto;
        }).collect(Collectors.toList());
    }
}