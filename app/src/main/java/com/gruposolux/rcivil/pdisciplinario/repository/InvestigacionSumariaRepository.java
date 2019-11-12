package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestigacionSumaria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestigacionSumariaRepository extends JpaRepository<InvestigacionSumaria, Long> {

}

