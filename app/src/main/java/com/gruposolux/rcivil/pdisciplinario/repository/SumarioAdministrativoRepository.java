package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SumarioAdministrativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SumarioAdministrativoRepository extends JpaRepository<SumarioAdministrativo, Long> {

}
