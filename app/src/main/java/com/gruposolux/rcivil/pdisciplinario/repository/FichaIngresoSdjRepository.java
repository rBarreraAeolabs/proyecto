package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.FichaIngresoSdj;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FichaIngresoSdj entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichaIngresoSdjRepository extends JpaRepository<FichaIngresoSdj, Long> {

}
