package com.esi.msformation.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    private String niveau;

    @OneToMany(mappedBy = "formation", fetch = FetchType.EAGER)
    private List<Modul> moduls;
    @ManyToOne
    private Enseignant responsable;

    private Long faculteId;

}
