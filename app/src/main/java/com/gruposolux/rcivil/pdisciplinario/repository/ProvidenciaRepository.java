package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Providencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvidenciaRepository extends JpaRepository<Providencia, Long> {

    @Query(value = "select distinct providencia from Providencia providencia",
        countQuery = "select count(distinct providencia) from Providencia providencia")
    Page<Providencia> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct providencia from Providencia providencia")
    List<Providencia> findAllWithEagerRelationships();

    @Query("select providencia from Providencia providencia where providencia.id =:id")
    Optional<Providencia> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("SELECT p FROM Providencia p WHERE p.id <> :providenciaId AND p.runImplicado = :runImplicado OR p.entidadImplicada = :entidadImplicada")
    List<Providencia> findAllByRunOrEntidadImplicada(@Param("runImplicado") String runImplicado,
                                                     @Param("entidadImplicada") Entidad entidad,
                                                     @Param("providenciaId") Long providenciaId);

    @Query("SELECT p FROM Providencia p WHERE p.numero = :numeroReferencia")
    Optional<Providencia> findByNumero(@Param("numeroReferencia") Long numeroReferencia);

    @Modifying
    @Query(value = "UPDATE providencia SET providencia_madre_id = ? WHERE id = ?", nativeQuery = true)
    void updateProvidenciaMadre(Long providenciaMadreId, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET numero = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroReferencia(Long numeroReferencia, Long providenciaId);
}
