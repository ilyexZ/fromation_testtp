package com.esi.msformation.Controler;


import com.esi.msformation.DTO.FormationResponse;
import com.esi.msformation.Entities.Formation;
import com.esi.msformation.Repository.FormationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/form")
@RequiredArgsConstructor
public class FormationControler {
    private final FormationRepository formationRepository;

    @GetMapping("/faculte/{faculteId}")
    public List<FormationResponse> getByFaculte(@PathVariable Long faculteId) {
        return formationRepository.findByFaculteId(faculteId)
                .stream()
                .map(FormationResponse::from)
                .collect(Collectors.toList());
    }
}