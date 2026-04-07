package com.esi.msuniversite.Controler;

import com.esi.msuniversite.DTO.FormationDTO;
import com.esi.msuniversite.DTO.ModuleDTO;
import com.esi.msuniversite.Entities.Faculte;
import com.esi.msuniversite.Feign.FormationFeignClient;
import com.esi.msuniversite.Repository.FaculteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FaculteGraphQLController {

    private final FaculteRepository faculteRepository;
    private final FormationFeignClient formationFeignClient;

    @QueryMapping
    public List<Faculte> allFacultesWithFormations() {
        return faculteRepository.findAll();
    }

    @SchemaMapping(typeName = "FaculteDetail", field = "formations")
    public List<FormationDTO> formations(Faculte faculte) {
        return formationFeignClient.getFormationsByFaculte(faculte.getId());
    }

    @SchemaMapping(typeName = "FormationDetail", field = "modules")
    public List<ModuleDTO> modules(FormationDTO formation) {
        return formation.getModules();
    }
}