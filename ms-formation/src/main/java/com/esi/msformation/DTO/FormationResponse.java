package com.esi.msformation.DTO;

import com.esi.msformation.Entities.Formation;
import com.esi.msformation.Entities.Modul;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FormationResponse {
    private String intitule;
    private List<ModuleResponse> modules;

    public static FormationResponse from(Formation f) {
        FormationResponse r = new FormationResponse();
        r.setIntitule(f.getIntitule());
        r.setModules(f.getModuls().stream().map(m -> {
            ModuleResponse mr = new ModuleResponse();
            mr.setTitre(m.getTitre());
            mr.setNomEnseignant(m.getEnseignants() != null ? m.getEnseignants().getNom() : "N/A");
            return mr;
        }).collect(Collectors.toList()));
        return r;
    }

    @Data
    public static class ModuleResponse {
        private String titre;
        private String nomEnseignant;
    }
}