package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sneiraillanes on 24-04-2019.
 */
@SuppressWarnings("unused")
@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long> {

}
