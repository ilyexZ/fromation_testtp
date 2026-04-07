package com.esi.msformation;

import com.esi.msformation.Entities.Enseignant;
import com.esi.msformation.Entities.Formation;
import com.esi.msformation.Entities.Modul;
import com.esi.msformation.Repository.EnseignantRepository;
import com.esi.msformation.Repository.FormationRepository;
import com.esi.msformation.Repository.ModulRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class MsFormationApplication implements CommandLineRunner {
    private final EnseignantRepository enseignantRepository;
    private final FormationRepository formationRepository;
    private final ModulRepository modulRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsFormationApplication.class, args);
    }

    @java.lang.Override
    public void run(java.lang.String... args) throws Exception {
        Enseignant e1 = new Enseignant(null, "Ali", "ali@uni.dz", "MCA");
        Enseignant e2 = new Enseignant(null, "Sara", "sara@uni.dz", "MCB");
        Enseignant e3 = new Enseignant(null, "Yacine", "yacine@uni.dz", "Professeur");

        enseignantRepository.saveAll(List.of(e1, e2, e3));

        // 🔹 Formation 1 (Faculté 1)
        Formation f1 = new Formation();
        f1.setIntitule("Informatique");
        f1.setNiveau("Licence");
        f1.setResponsable(e1);
        f1.setFaculteId(1L);

        // 🔹 Formation 2 (Faculté 1)
        Formation f2 = new Formation();
        f2.setIntitule("Intelligence Artificielle");
        f2.setNiveau("Master");
        f2.setResponsable(e3);
        f2.setFaculteId(1L);

        // 🔹 Formation 3 (Faculté 2)
        Formation f3 = new Formation();
        f3.setIntitule("Réseaux");
        f3.setNiveau("Licence");
        f3.setResponsable(e2);
        f3.setFaculteId(2L);

        formationRepository.saveAll(List.of(f1, f2, f3));

        // 🔹 Modules for f1
        Modul m1 = new Modul(null, "POO", 60, f1, e1);
        Modul m2 = new Modul(null, "Bases de données", 45, f1, e2);

        // 🔹 Modules for f2
        Modul m3 = new Modul(null, "Machine Learning", 50, f2, e3);
        Modul m4 = new Modul(null, "Deep Learning", 40, f2, e3); // same enseignant

        // 🔹 Modules for f3
        Modul m5 = new Modul(null, "Réseaux 1", 55, f3, e2);
        Modul m6 = new Modul(null, "Sécurité Réseau", 35, f3, e1); // cross-teaching

        modulRepository.saveAll(List.of(m1, m2, m3, m4, m5, m6));
    }
}
