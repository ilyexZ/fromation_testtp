package com.esi.msuniversite.DTO;

import lombok.Data;
import java.util.List;

@Data
public class UniversiteDetailDTO {
    private Long id;
    private String nom;
    private String ville;
    private EnseignantDTO recteur;
    private List<EnseignantDTO> doyens;
}