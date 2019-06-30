package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Plantilla;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Plantilla entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {

}

