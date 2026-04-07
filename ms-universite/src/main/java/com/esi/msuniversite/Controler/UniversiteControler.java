package com.esi.msuniversite.Controler;


import com.esi.msuniversite.Entities.Universite;
import com.esi.msuniversite.Repository.UniversityRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/uni")
@RequiredArgsConstructor
public class UniversiteControler {

   private final   UniversityRepository universityRepository;
    @GetMapping("universites")

    public List<Universite> getUniversites(){
        return universityRepository.findAll();
    }
}
