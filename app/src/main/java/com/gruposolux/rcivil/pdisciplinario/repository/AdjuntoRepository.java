package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.MovimientoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Spring Data  repository for the Adjunto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Long> {

    Adjunto findByHash(String hash);

    Adjunto findAdjuntoById(Long id);

    List<Adjunto> findByProvidencia(Providencia providencia);

    @Modifying
    @Transactional
    @Query("update Adjunto set providencia.id = :providencia, estado = :status where hash in (:hashes)")
    void confirmarComoIngresoProvidencia(@Param("providencia") Long providencia, @Param("status")FileUploadStatus status, @Param("hashes")ArrayList<String> hashes);

    @Modifying
    @Transactional
    @Query("update Adjunto set localPath = :newPath where hash = :hash")
    void actualizarFilePath(@Param("hash") String hash, @Param("newPath") String localPath);

    @Modifying
    @Transactional
    @Query("UPDATE Adjunto SET movimientoProvidencia = :movProv, providencia = :prov WHERE id = :idAdjunto")
    void updateMovimiento(@Param("movProv") MovimientoProvidencia movProv, @Param("prov") Providencia prov, @Param("idAdjunto") Long idAdjunto);
}


