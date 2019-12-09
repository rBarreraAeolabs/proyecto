package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Entidad;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Query("select providencia from Providencia providencia where providencia.id =:id")
    Providencia findOne(@Param("id") Long id);

    @Query("SELECT p FROM Providencia p WHERE p.id <> :providenciaId AND p.runImplicado = :runImplicado OR p.entidadImplicada = :entidadImplicada")
    List<Providencia> findAllByRunOrEntidadImplicada(@Param("runImplicado") String runImplicado,
                                                     @Param("entidadImplicada") Entidad entidad,
                                                     @Param("providenciaId") Long providenciaId);

    @Query(value = "SELECT * FROM Providencia p WHERE p.numero_referencia = :numeroReferencia AND p.requisito = 'FISCAL_RECHAZO' " +
        "ORDER BY p.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    List<Providencia> findByNumeroRefeSeleccionFiscal(@Param("numeroReferencia") Long numeroReferencia);

    @Query(value = "SELECT * FROM Providencia p WHERE p.numero_referencia = :numeroReferencia AND p.requisito = 'SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO' " +
        "ORDER BY p.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    List<Providencia> findByNumeroRefeOrdenJuridico(@Param("numeroReferencia") Long numeroReferencia);

    @Query(value = "SELECT * FROM Providencia p WHERE p.numero_referencia = :numeroReferencia AND p.etapa = 'IJ_PROVIDENCIA_SANCIONAR' " +
        "ORDER BY p.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    List<Providencia> findByNumeroRefeSeleccionApelacion(@Param("numeroReferencia") Long numeroReferencia);

    @Query("SELECT p FROM Providencia p WHERE p.numeroReferencia = :numeroReferencia")
    Providencia findByNumero(@Param("numeroReferencia") Long numeroReferencia);

    @Query("SELECT p FROM Providencia p WHERE p.numeroReferencia = :numeroReferencia")
    List<Providencia> buscarPornroReferencia(@Param("numeroReferencia") Long numeroReferencia);

    @Query(value = "SELECT p FROM Providencia p where p.fechaCreacion in  (select max (p2.fechaCreacion) from Providencia p2 group by p2.numeroReferencia)")
    Set<Providencia> findByAllnroReferencia();

    @Query("SELECT p FROM Providencia p WHERE p.tipo = :tipoSolicitud")
    Optional<Providencia> findByTipo(@Param("tipoSolicitud") String tipoSolicitud);

    @Query(value = "SELECT numero_referencia FROM Providencia p WHERE p.id = :iDMadre ", nativeQuery = true)
    Long findNumberReferentForID(@Param("iDMadre") Long iDMadre);

    @Query(value = "SELECT * FROM Providencia p WHERE p.numero_referencia = :numberRefere ORDER BY p.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    List<Providencia> findForNumberReferent(@Param("numberRefere") Long numberRefere);

    @Modifying
    @Query(value = "UPDATE providencia SET providencia_madre_id = ? WHERE id = ? ", nativeQuery = true)
    void updateProvidenciaMadre(Long providenciaMadreId, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET numero_referencia = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroReferencia(Long numeroReferencia, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET numero_dgd = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroDGD(Long numeroDGD, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET numero_dgdp = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroDGDP(Long numeroDGDP, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET numero_providencia = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroProvidencia (Long numeroProvidencia, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET folio = ? WHERE id = ?", nativeQuery = true)
    void updateNumeroFolio(Long numeroFolio, Long providenciaId);

    @Modifying
    @Query(value = "UPDATE providencia SET tipo = ? WHERE id = ?", nativeQuery = true)
    void updateTipoSolicitud(String tipoSolicitud, Long providenciaId);

    //actualizar requisito de prorroga madre
    @Modifying
    @Query(value = "UPDATE providencia   SET requisito = 'VEREMOS' where id =:id ",nativeQuery = true)
    void  updateRequisito(@Param("id") Long id);

    @Query(value = "SELECT requisito FROM Providencia p WHERE p.numero_referencia = :numeroReferencia ORDER BY p.fecha_creacion DESC LIMIT 1", nativeQuery = true)
    EstadoProvidencia findRequisitoByNumberRefer(@Param("numeroReferencia")Long numeroReferencia);

    @Query(value = "SELECT requisito FROM Providencia p WHERE p.providencia_madre_id = :iDMadre ORDER BY p.fecha_creacion ASC LIMIT 1;", nativeQuery = true)
    EstadoProvidencia findRequisitoForProrroga(@Param("iDMadre")Long iDMadre);

    @Query(value = "SELECT requisito FROM Providencia p WHERE p.id = :iDMadre ORDER BY p.fecha_creacion ASC LIMIT 1;", nativeQuery = true)
    EstadoProvidencia findRequisitoByIdMadre(@Param("iDMadre")Long iDMadre);

    @Query(value = "SELECT providencia_madre_id FROM Providencia p WHERE p.id = :iD ;", nativeQuery = true)
    Long findIDMadre(@Param("iD")Long iD);

    @Query(value = "SELECT numero_referencia FROM providencia p WHERE p.numero_referencia = :numeroRefer LIMIT 1;", nativeQuery = true)
    Long existeNumeroReferencia(@Param("numeroRefer")Long numeroRefer);

    @Query(value = "SELECT providencia_madre_id FROM providencia p WHERE p.providencia_madre_id = ?", nativeQuery = true)
    Long findExisteHija(Long idMadreBuscar);


    @Query(value = "SELECT numero_dgd FROM providencia p WHERE p.id = :id", nativeQuery = true)
    Long findnumeroDgd(@Param("id") Long iDProvidencia);


    @Query(value = "SELECT numero_dgdp FROM providencia p WHERE p.id = :id", nativeQuery = true)
    Long findnumeroDgdp(@Param("id") Long iDProvidencia);


}
