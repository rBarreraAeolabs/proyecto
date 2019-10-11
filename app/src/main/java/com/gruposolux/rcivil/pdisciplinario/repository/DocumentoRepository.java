package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


/**
 * Spring Data  repository for the Documento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("SELECT d FROM Documento d WHERE d.hash = :hash")
    Documento findByHash(@Param("hash") String hash);

    @Query("SELECT d FROM Documento d WHERE d.providencia = :providencia AND d.tipoPlantilla = 'RESOLUCION' " +
        "AND d.numeroResolucion is null ORDER BY d.version DESC")
    Set<Documento> findByProvidencia(@Param("providencia") Providencia providencia);

    @Query("SELECT COUNT(d) FROM Documento d")
    Long getCountTotal();

    @Query("SELECT d FROM Documento d WHERE LOWER(d.archivoNombre) = :nombreArchivo AND d.providencia = :providencia AND " +
        "d.numeroResolucion is null ORDER BY d.version DESC")
    Set<Documento> findResolucionByNombreArchivo(@Param("nombreArchivo") String nombreArchivo, @Param("providencia") Providencia providencia);

    @Query("SELECT d FROM Documento d WHERE LOWER(d.archivoNombre) = :nombreArchivo AND d.providencia = :providencia " +
        "ORDER BY d.version DESC")
    Set<Documento> findByNombreArchivo(@Param("nombreArchivo") String nombreArchivo, @Param("providencia") Providencia providencia);

    @Query(value=" select count(d.providencia) from Documento d  where d.providencia.id = :id")
    Integer Contar(@Param("id") Long id);


}
