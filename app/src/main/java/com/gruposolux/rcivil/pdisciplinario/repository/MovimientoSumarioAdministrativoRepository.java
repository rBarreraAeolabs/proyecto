package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoSumarioAdministrativo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MovimientoSumarioAdministrativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoSumarioAdministrativoRepository extends JpaRepository<MovimientoSumarioAdministrativo, Long> {

}
