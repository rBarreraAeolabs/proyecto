package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the MovimientoProvidencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimientoProvidenciaRepository extends JpaRepository<MovimientoProvidencia, Long> {
    @Query("SELECT m FROM MovimientoProvidencia m INNER JOIN m.providencia p WHERE p = :providencia " +
        "ORDER BY m.fecha asc")
    Set<MovimientoProvidencia> findByProvidencia(@Param("providencia") Providencia providencia);

//    @Query("SELECT m FROM MovimientoProvidencia m INNER JOIN m.providencia p WHERE p = :providencia " +
//        "ORDER BY m.fecha desc")
//    List<MovimientoProvidencia> findByProvidencia(@Param("providencia") Providencia providencia);

    // Set<MovimientoProvidencia> findByProvidenciaOrderByProvidenciaDesc(Providencia providencia);
}
