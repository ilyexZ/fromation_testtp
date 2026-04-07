package com.esi.msformation.Controler;

import com.esi.msformation.Entities.Enseignant;
import com.esi.msformation.Repository.EnseignantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ens")
@RequiredArgsConstructor
public class EnsegnantControler {
    private final EnseignantRepository enseignantRepository;
    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants(){
        return ResponseEntity.ok(

        enseignantRepository.findAll().stream().toList()
        );
    }
    @GetMapping("/{id}")
    public Enseignant getById(@PathVariable Long id) {
        return enseignantRepository.findById(id).orElse(null);
    }
}
