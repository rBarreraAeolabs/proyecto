package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Plazo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlazoRepository extends JpaRepository<Plazo, Long> {

}
