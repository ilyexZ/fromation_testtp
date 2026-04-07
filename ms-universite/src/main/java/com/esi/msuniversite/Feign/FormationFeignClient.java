package com.esi.msuniversite.Feign;

import com.esi.msuniversite.DTO.EnseignantDTO;
import com.esi.msuniversite.DTO.FormationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ms-formation")
public interface FormationFeignClient {

    @GetMapping("/api/ens/{id}")
    EnseignantDTO getEnseignantById(@PathVariable Long id);

    @GetMapping("/api/form/faculte/{faculteId}")
    List<FormationDTO> getFormationsByFaculte(@PathVariable Long faculteId);
}