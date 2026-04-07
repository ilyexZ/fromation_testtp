package com.esi.msuniversite;

import com.esi.msuniversite.Entities.Faculte;
import com.esi.msuniversite.Entities.Universite;
import com.esi.msuniversite.Repository.FaculteRepository;
import com.esi.msuniversite.Repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor

@EnableFeignClients
@EnableCaching
public class MsUniversiteApplication implements CommandLineRunner {
    private final UniversityRepository universityRepository;
    private final FaculteRepository faculteRepository;

    final RepositoryRestConfiguration config;


    public static void main(String[] args) {
        SpringApplication.run(MsUniversiteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        config.exposeIdsFor(Universite.class, Faculte.class);

        // 🔹 Universités
        Universite u1 = new Universite(null, "Djilali Lyabess", "SBA",null, null);
        Universite u2 = new Universite(null, "Abdelhamid Ibn Badis", "Oran",null, null);

        universityRepository.saveAll(List.of(u1, u2));

        // 🔹 Facultés (last param = doyenId)
        Faculte f1 = new Faculte(null, "Faculté Electronique", "Electronique", u1, 1L);
        Faculte f2 = new Faculte(null, "Faculté Informatique", "Informatique", u1, 2L);
        Faculte f3 = new Faculte(null, "Faculté Droit", "Droit", u2, 3L);

        faculteRepository.saveAll(List.of(f1, f2, f3));
    }
}
