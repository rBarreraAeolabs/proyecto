package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Respuesta;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by sneiraillanes on 22-04-2019.
 */
@SuppressWarnings("unused")
@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("SELECT r FROM Respuesta r LEFT JOIN r.documentos LEFT JOIN r.adjuntos " +
        "WHERE r.providencia = :providencia AND r.movimientoProvidencia is null AND r.user = :userLogged AND " +
        "r.estadoProvidencia = :estadoProvidencia")
    Optional<Respuesta> findByProvidencia(@Param("providencia") Providencia providencia, @Param("userLogged") User userLogged,
                                          @Param("estadoProvidencia") String estadoProvidencia);

    @Query(value = "SELECT id FROM respuesta WHERE movimiento_providencia_id = ? AND providencia_id NOTNULL " +
        "AND user_id NOTNULL AND estado_providencia NOTNULL", nativeQuery = true)
    Long findByMovimientoPrividencia(Long movimientoProvidenciaId);

    @Modifying
    @Query(value = "UPDATE respuesta SET movimiento_providencia_id = ?, guardada = FALSE " +
        "WHERE providencia_id = ? AND movimiento_providencia_id is null AND user_id = ? AND estado_providencia = ?",
        nativeQuery = true)
    void updateMovimientoProvidencia(Long movimientoProvidenciaId, Long providenciaId, Long userId, String estadoProvidencia);
}
