package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoInvestigacionSumaria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MovimientoInvestigacionSumaria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoInvestigacionSumariaRepository extends JpaRepository<MovimientoInvestigacionSumaria, Long> {

}

